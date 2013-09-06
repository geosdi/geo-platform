package org.geosdi.geoplatform.gui.client.widget;

import com.extjs.gxt.ui.client.Registry;
import com.google.common.collect.Lists;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DeckLayoutPanel;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import java.util.List;
import org.geosdi.geoplatform.gui.client.event.IBackNotifyHandler;
import org.geosdi.geoplatform.gui.client.event.INextNotifyHandler;
import org.geosdi.geoplatform.gui.configuration.users.options.member.UserSessionEnum;
import org.geosdi.geoplatform.gui.global.security.IGPAccountDetail;
import org.geosdi.geoplatform.gui.model.message.IGPClientMessage;
import org.geosdi.geoplatform.gui.puregwt.GPHandlerManager;

/**
 * @author Nazzareno Sileno - CNR IMAA geoSDI Group
 * @email nazzareno.sileno@geosdi.org
 */
public class NotificationCenter extends Composite implements IBackNotifyHandler,
        INextNotifyHandler {

    private static BinderDeckLayoutUiBinder uiBinder = GWT.create(BinderDeckLayoutUiBinder.class);

    interface BinderDeckLayoutUiBinder extends
            UiBinder<Widget, NotificationCenter> {
    }
    @UiField
    VerticalPanel verticalPanel;
    //
    private DeckLayoutPanel deckLayoutPanel = new DeckLayoutPanel();
    private List<SingleNotify> listComponent = Lists.<SingleNotify>newArrayList();

    public NotificationCenter() {
        super.initWidget(uiBinder.createAndBindUi(this));
        GPHandlerManager.addHandler(IBackNotifyHandler.TYPE, this);
        GPHandlerManager.addHandler(INextNotifyHandler.TYPE, this);
        this.initComponent();
        LayoutPanel layoutPanel = new LayoutPanel();
        layoutPanel.setPixelSize(400, 300);
        layoutPanel.add(deckLayoutPanel);
        deckLayoutPanel.insert(new NotifyListPanel(listComponent,
                deckLayoutPanel), 0);
        this.verticalPanel.add(layoutPanel);
        deckLayoutPanel.showWidget(0);
    }

    private void initComponent() {
        IGPAccountDetail accountDetail = Registry.get(UserSessionEnum.ACCOUNT_DETAIL_IN_SESSION.name());
        List<IGPClientMessage> messageList = accountDetail.getUnreadMessages();
        if (messageList != null && !messageList.isEmpty()) {
            for (IGPClientMessage message : messageList) {
                SingleNotify component = new SingleNotify(message);
                listComponent.add(component);
            }
        } else {
            //TODO: Inserire un messaggio per avvertire che non ci sono messaggi
            // non letti
            System.out.println(" Non ci sono messaggi non letti ");
        }
    }

    @Override
    public void onClickNext(int index) {
        this.deckLayoutPanel.showWidget(index);
    }

    @Override
    public void onClickBack(int index) {
        this.deckLayoutPanel.showWidget(index);
    }
}
