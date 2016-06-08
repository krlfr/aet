/*
 * Cognifide AET :: Maven Plugin
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
package com.cognifide.aet.xmlparser.xml.models;

import com.cognifide.aet.communication.api.config.TestRun;
import com.cognifide.aet.communication.api.config.TestSuiteRun;
import com.cognifide.aet.xmlparser.api.ParseException;
import com.cognifide.aet.xmlparser.xml.utils.ValidationUtils;
import com.google.common.collect.Lists;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.core.Validate;

import java.util.List;

/**
 * @author lukasz.wieczorek
 */
@Root
public class TestSuite {

	private static final String ATTRIBUTE_PARENT_NAME = "suite";

	@ElementList(inline = true)
	private List<Test> tests;

	@Element
	private Reports reports;

	@Attribute
	private String company;

	@Attribute
	private String project;

	@Attribute
	private String name;

	@Attribute
	private String environment;

	@Attribute(required = false)
	private String domain;

	public TestSuiteRun adaptToTestSuiteRun() throws ParseException {
		List<TestRun> testRuns = Lists.newArrayList();
		List<ParseException> errors = Lists.newArrayList();
		for (Test test : tests) {
			try {
				testRuns.add(test.adaptToTestRun());
			} catch (ParseException e) {
				errors.add(e);
			}
		}

		if (!errors.isEmpty()) {
			if (errors.size() == 1) {
				throw errors.remove(0);
			} else {
				ParseException e = errors.remove(0);
				StringBuilder builder = new StringBuilder(String.format(
						"More then one error occurs during Test Suite parsing:%n%s%n", e.getMessage()));

				for (ParseException error : errors) {
					builder.append(String.format("%n%s%n", error.getMessage()));
					e.addSuppressed(error);
				}

				throw new ParseException(builder.toString(), e);
			}
		}
		return new TestSuiteRun(name, company, project, environment, domain, testRuns,
				reports.adaptToReporterSteps());
	}

	@Validate
	public void validate() throws ParseException {
		this.company = ValidationUtils
				.validateNotHavingUnderscore(ValidationUtils.validateCaseSensitiveNameAttribute(
						ATTRIBUTE_PARENT_NAME, "company", company),
						"Company can't contains underscore character. It is connected with the way how Version API reads companies. Underscores has been stripped.");
		this.name = ValidationUtils.validateCaseSensitiveNameAttribute(ATTRIBUTE_PARENT_NAME, "name", name);
		this.project = ValidationUtils.validateCaseSensitiveNameAttribute(ATTRIBUTE_PARENT_NAME, "project",
				project);
	}

}
