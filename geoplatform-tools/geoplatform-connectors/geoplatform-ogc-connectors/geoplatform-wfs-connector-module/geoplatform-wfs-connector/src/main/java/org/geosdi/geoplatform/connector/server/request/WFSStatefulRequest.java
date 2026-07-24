/*
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2026 geoSDI Group (CNR IMAA - Potenza - ITALY).
 *
 *   This program is free software: you can redistribute it and/or modify it
 *   under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version. This program is distributed in the
 *   hope that it will be useful, but WITHOUT ANY WARRANTY; without
 *   even the implied warranty of MERCHANTABILITY or FITNESS FOR
 *   A PARTICULAR PURPOSE. See the GNU General Public License
 *   for more details. You should have received a copy of the GNU General
 *   Public License along with this program. If not, see http://www.gnu.org/licenses/
 *
 *   ====================================================================
 *
 *   Linking this library statically or dynamically with other modules is
 *   making a combined work based on this library. Thus, the terms and
 *   conditions of the GNU General Public License cover the whole combination.
 *
 *   As a special exception, the copyright holders of this library give you permission
 *   to link this library with independent modules to produce an executable, regardless
 *   of the license terms of these independent modules, and to copy and distribute
 *   the resulting executable under terms of your choice, provided that you also meet,
 *   for each linked independent module, the terms and conditions of the license of
 *   that module. An independent module is a module which is not derived from or
 *   based on this library. If you modify this library, you may extend this exception
 *   to your version of the library, but you are not obligated to do so. If you do not
 *   wish to do so, delete this exception statement from your version.
 */
package org.geosdi.geoplatform.connector.server.request;

import javax.annotation.Nonnull;
import java.util.function.Consumer;

import static javax.annotation.meta.When.NEVER;

/**
 * Common contract shared by the fluent, thread-safe WFS requests whose per-thread configuration is held in
 * {@link ThreadLocal} state (GetFeature, DescribeFeatureType, Transaction). It hoists the {@link #clearState()}
 * declaration - previously duplicated on each request interface - to a single place, and layers on top the
 * {@link #execute} template, which owns the whole configure &rarr; terminal &rarr; cleanup lifecycle so the
 * per-thread state is released on <b>every</b> path (normal return <b>and</b> exception) without relying on the
 * caller to remember a {@code finally clearState()}.
 * <p>
 * <b>When to use this</b> : {@code execute} matters only when a <b>single request instance is shared and
 * reused across many threads</b> (e.g. the request is cached / held as a long-lived field). In that case the
 * {@code ThreadLocal} entries keyed by the shared instance are never garbage-collected, so a thread would
 * otherwise retain its last configuration for the whole life of that instance : routing the usage through
 * {@code execute} makes the {@code clearState()} cleanup structurally impossible to skip while still allowing
 * several terminal calls within the same action. If instead a fresh request is created per call - the default
 * done by {@code createXxxRequest()} in the production services - the per-thread state dies with the instance
 * and neither {@code execute} nor {@code clearState()} is required.
 * <p>
 * {@code SELF} is the recursive self-type of the concrete fluent request, so the action passed to
 * {@code execute} sees the concrete {@code withXxx(...)} mutators and terminal methods.
 *
 * @param <T>    the response type produced by the request terminal methods.
 * @param <SELF> the concrete fluent request type.
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface WFSStatefulRequest<T, SELF extends WFSStatefulRequest<T, SELF>> extends IGPostConnectorRequest<T> {

    /**
     * Releases the per-thread configuration held in the {@code ThreadLocal} state of this request. This is an
     * <b>opt-in</b> operation : it is <b>not</b> invoked automatically by the terminal methods, because the
     * request state must survive the whole request/response lifecycle (the response reader may still read it
     * after the request is sent) and a configured request may be reused across several terminal calls. Invoke
     * it explicitly - or, preferably, let {@link #execute} invoke it for you - when a shared request instance
     * is done being used on a pooled thread, to avoid retaining per-thread values. Subsequent
     * {@code withXxx(...)} calls re-initialize the state.
     */
    void clearState();

    /**
     * Runs {@code action} against this request and then <b>always</b> releases the per-thread state via
     * {@link #clearState()}, whether the action returns normally or throws. Several terminal calls may be
     * issued inside the action (e.g. log {@code showRequestAsString()} then {@code getResponseAsStream(...)})
     * : the state is released only once the action completes.
     *
     * @param action the operation to perform against the (already configured) request; must not be {@code null}.
     * @param <R>    the type produced by the action.
     * @return the value produced by the action.
     * @throws Exception if the action throws (propagated after the state has been released).
     */
    default <R> R execute(@Nonnull(when = NEVER) WFSTerminalFunction<? super SELF, R> action) throws Exception {
        if (action == null)
            throw new IllegalArgumentException("The Parameter action must not be null.");
        @SuppressWarnings("unchecked")
        SELF request = (SELF) this;
        try {
            return action.apply(request);
        } finally {
            this.clearState();
        }
    }

    /**
     * Convenience overload that first applies {@code configurator} (the fluent {@code withXxx(...)} chain) and
     * then runs {@code terminal}, releasing the per-thread state afterwards on every path exactly like
     * {@link #execute(WFSTerminalFunction)}.
     *
     * @param configurator configures the request through its fluent mutators; must not be {@code null}.
     * @param terminal     the terminal operation producing the result; must not be {@code null}.
     * @param <R>          the type produced by the terminal operation.
     * @return the value produced by {@code terminal}.
     * @throws Exception if the configurator or the terminal throws (propagated after the state has been released).
     */
    default <R> R execute(@Nonnull(when = NEVER) Consumer<? super SELF> configurator,
                          @Nonnull(when = NEVER) WFSTerminalFunction<? super SELF, R> terminal) throws Exception {
        if (configurator == null)
            throw new IllegalArgumentException("The Parameter configurator must not be null.");
        if (terminal == null)
            throw new IllegalArgumentException("The Parameter terminal must not be null.");
        return this.execute(request -> {
            configurator.accept(request);
            return terminal.apply(request);
        });
    }
}