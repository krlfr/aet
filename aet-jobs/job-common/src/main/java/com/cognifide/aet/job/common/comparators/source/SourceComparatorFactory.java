/*
 * Cognifide AET :: Job Common
 *
 * Copyright (C) 2013 Cognifide Limited
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.cognifide.aet.job.common.comparators.source;

import com.cognifide.aet.job.api.comparator.ComparatorFactory;
import com.cognifide.aet.job.api.comparator.ComparatorJob;
import com.cognifide.aet.job.api.datamodifier.DataModifierJob;
import com.cognifide.aet.job.api.exceptions.ParametersException;
import com.cognifide.aet.job.common.comparators.source.diff.DiffParser;
import com.cognifide.aet.vs.ComparatorProperties;
import com.cognifide.aet.vs.Node;

import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Service;

import java.util.List;
import java.util.Map;

@Component
@Service
public class SourceComparatorFactory implements ComparatorFactory {

	@Override
	public final String getType() {
		return SourceComparator.COMPARATOR_TYPE;
	}

	@Override
	public final String getName() {
		return SourceComparator.COMPARATOR_NAME;
	}

	@Override
	public final int getRanking() {
		return DEFAULT_COMPARATOR_RANKING;
	}

	@Override
	public ComparatorJob createInstance(Node dataNode, Node patternNode, Node resultNode,
			Map<String, String> params, ComparatorProperties comparatorProperties,
			List<DataModifierJob> dataModifierJobs) throws ParametersException {
		final SourceComparator sourceComparator = new SourceComparator(dataNode, patternNode, resultNode,
				comparatorProperties, new DiffParser(), dataModifierJobs);
		sourceComparator.setParameters(params);
		return sourceComparator;
	}

}
