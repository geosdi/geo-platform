/*
 *  geo-platform
 *  Rich webgis framework
 *  http://geo-platform.org
 * ====================================================================
 *
 * Copyright (C) 2008-2015 geoSDI Group (CNR IMAA - Potenza - ITALY).
 *
 * This program is free software: you can redistribute it and/or modify it 
 * under the terms of the GNU General Public License as published by 
 * the Free Software Foundation, either version 3 of the License, or 
 * (at your option) any later version. This program is distributed in the 
 * hope that it will be useful, but WITHOUT ANY WARRANTY; without 
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR 
 * A PARTICULAR PURPOSE. See the GNU General Public License 
 * for more details. You should have received a copy of the GNU General 
 * Public License along with this program. If not, see http://www.gnu.org/licenses/ 
 *
 * ====================================================================
 *
 * Linking this library statically or dynamically with other modules is 
 * making a combined work based on this library. Thus, the terms and 
 * conditions of the GNU General Public License cover the whole combination. 
 * 
 * As a special exception, the copyright holders of this library give you permission 
 * to link this library with independent modules to produce an executable, regardless 
 * of the license terms of these independent modules, and to copy and distribute 
 * the resulting executable under terms of your choice, provided that you also meet, 
 * for each linked independent module, the terms and conditions of the license of 
 * that module. An independent module is a module which is not derived from or 
 * based on this library. If you modify this library, you may extend this exception 
 * to your version of the library, but you are not obligated to do so. If you do not 
 * wish to do so, delete this exception statement from your version. 
 *
 */
package org.geosdi.geoplatform.experimental.el.dao;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;

import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.count.CountRequestBuilder;
import org.elasticsearch.action.count.CountResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
//import org.elasticsearch.common.collect.Lists;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.SearchHit;
import org.geosdi.geoplatform.experimental.el.api.mapper.GPBaseMapper;
import org.geosdi.geoplatform.experimental.el.api.model.Document;
import org.geosdi.geoplatform.experimental.el.configurator.GPIndexConfigurator;
import org.geosdi.geoplatform.experimental.el.dao.GPElasticSearchDAO.GPElasticSearchBaseDAO;
import org.geosdi.geoplatform.experimental.el.index.GPIndexCreator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @param <D>
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public abstract class AbstractElasticSearchDAO<D extends Document> implements GPElasticSearchBaseDAO<D> {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	//
	private GPIndexCreator indexCreator;
	protected GPBaseMapper<D> mapper;
	protected Client elastichSearchClient;

	@Override
	public D persist(D document) throws Exception {
		logger.debug("#################Try to insert {}\n\n", document);
		IndexResponse response;

		if (document.isIdSetted()) {
			response = this.elastichSearchClient.prepareIndex(getIndexName(), getIndexType(), document.getId())
					.setSource(this.mapper.writeAsString(document)).get();
		} else {
			response = this.elastichSearchClient.prepareIndex(getIndexName(), getIndexType())
					.setSource(this.mapper.writeAsString(document)).get();
			document.setId(response.getId());
			update(document);
		}
		logger.debug("##############{} Created : {}\n\n", this.mapper.getDocumentClassName(), response.isCreated());

		return document;
	}

	@Override
	public void update(D document) throws Exception {
		Preconditions.checkArgument(
				((document != null) && ((document.getId() != null) && !(document.getId().isEmpty()))),
				"The {} to Update must" + " not be null or ID must not be null or Empty.",
				this.mapper.getDocumentClassName());
		logger.debug("################Try to Update : {}\n\n", document);

		this.elastichSearchClient.prepareUpdate(getIndexName(), getIndexType(), document.getId())
				.setDoc(this.mapper.writeAsString(document)).get();
	}

	@Override
	public BulkResponse persist(Iterable<D> documents) throws Exception {
		Preconditions.checkArgument(((documents != null)), "The Documents " + "to save, must not be null.");

		BulkRequestBuilder bulkRequest = this.elastichSearchClient.prepareBulk();
		for (D document : documents) {
			if (document.isIdSetted()) {
				bulkRequest.add(this.elastichSearchClient.prepareIndex(getIndexName(), getIndexType(), document.getId())
						.setSource(this.mapper.writeAsString(document)));
			} else {
				bulkRequest.add(this.elastichSearchClient.prepareIndex(getIndexName(), getIndexType())
						.setSource(this.mapper.writeAsString(document)));
			}
		}

		BulkResponse bulkResponse = bulkRequest.get();
		if (bulkResponse.hasFailures()) {
			throw new IllegalStateException(bulkResponse.buildFailureMessage());
		}
		return bulkResponse;
	}

	@Override
	public <P extends Page> IPageResult<D> find(P page) throws Exception {
		Preconditions.checkArgument((page != null), "Page must not be null.");
		SearchRequestBuilder builder = page
				.buildPage(this.elastichSearchClient.prepareSearch(getIndexName()).setTypes(getIndexType()));

		logger.trace("#########################Builder : {}\n\n", builder.toString());

		SearchResponse searchResponse = builder.get();

		if (searchResponse.status() != RestStatus.OK) {
			throw new IllegalStateException("Problem in Search : " + searchResponse.status());
		}

		Long total = searchResponse.getHits().getTotalHits();

		logger.debug("###################TOTAL HITS FOUND : {} .\n\n", total);

		List<D> documents = Lists.newArrayList();

		for (SearchHit searchHit : searchResponse.getHits().hits()) {
			D document = this.mapper.read(searchHit.getSourceAsString());
			if (!document.isIdSetted()) {
				document.setId(searchHit.getId());
			}
			documents.add(document);
		}

		return new PageResult<D>(total, documents);
	}

	@Override
	public void delete(String id) {
		Preconditions.checkArgument(((id != null) && !(id.isEmpty())), "The ID must not be null or an Empty String");
		DeleteResponse response = elastichSearchClient.prepareDelete(getIndexName(), getIndexType(), id).execute()
				.actionGet();
		if (response.isFound()) {
			logger.debug("#################Document with ID : {}, " + "was deleted.", id);
		} else {
			logger.debug("#################Document with ID : {}, " + "was not found in ElasticSearch.");
		}
	}

	@Override
	public D find(String id) throws Exception {
		Preconditions.checkArgument((id != null) && !(id.isEmpty()),
				"The ElasticSearch ID must not be null or an Empty String");

		GetResponse existResponse = elastichSearchClient.prepareGet(getIndexName(), getIndexType(), id).get();

		return (existResponse.isExists()) ? this.mapper.read(existResponse.getSourceAsString()) : null;
	}

	@Override
	public Long count() {
		SearchRequestBuilder builder = this.elastichSearchClient.prepareSearch(getIndexName()).setTypes(getIndexType());
		SearchResponse searchResponse = builder.get();
		return searchResponse.getHits().getTotalHits();
	}

	/**
	 * @param queryBuilder
	 * @return {@link Long}
	 * @throws Exception
	 */
	@Override
	public Long count(QueryBuilder queryBuilder) throws Exception {

		SearchRequestBuilder builder = this.elastichSearchClient.prepareSearch(getIndexName()).setQuery(queryBuilder)
				.setTypes(getIndexType());
		SearchResponse searchResponse = builder.get();
		return searchResponse.getHits().getTotalHits();

	}

	@Override
	public void removeAll() {
		this.elastichSearchClient.delete(new DeleteRequest(getIndexName())).actionGet();
	}

	/**
	 * @return The Index Name
	 */
	protected final String getIndexName() {
		return this.indexCreator.getIndexSettings().getIndexName();
	}

	/**
	 * @return The Index Type
	 */
	protected final String getIndexType() {
		return this.indexCreator.getIndexSettings().getIndexType();
	}

	/**
	 * <p>
	 * Remember Index Creation is called by
	 * {@link GPIndexConfigurator#configure()}
	 * </p>
	 *
	 * @throws Exception
	 */
	protected final void createIndex() throws Exception {
		this.indexCreator.createIndex();
	}

	/**
	 * <p>
	 * Dangerous. If called all Data will be dropped
	 * </p>
	 *
	 * @throws Exception
	 */
	protected final void deleteIndex() throws Exception {
		this.indexCreator.deleteIndex();
	}

	/**
	 * @return {@link Boolean}
	 * @throws Exception
	 */
	public Boolean existIndex() throws Exception {
		return this.indexCreator.existIndex();
	}

	/**
	 * @param theIndexCreator
	 */
	@Override
	public <IC extends GPIndexCreator> void setIndexCreator(IC theIndexCreator) {
		this.indexCreator = theIndexCreator;
	}

	@Override
	public final void afterPropertiesSet() throws Exception {
		Preconditions.checkNotNull(this.mapper, "The Mapper must not be null.");
		Preconditions.checkNotNull(this.indexCreator, "The Index Creator must " + "not be null.");

		this.elastichSearchClient = this.indexCreator.client();

		Preconditions.checkNotNull(this.elastichSearchClient, "The ElasticSearch Client must " + "not be null.");
	}

}
