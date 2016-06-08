/*
 * Cognifide AET :: Version Storage
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
package com.cognifide.aet.vs.rest.exceptions;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;

import javax.ws.rs.core.Response;

import org.junit.Test;
import org.mockito.Mockito;

import com.cognifide.aet.communication.api.OperationStatus;

/**
 * RestApiExceptionMapperTest
 * 
 * @Author: Maciej Laskowski
 * @Date: 12.03.15
 */
public class RestApiExceptionMapperTest {

	@Test
	public void toResponse_expectExceptionMessageInResponse() throws Exception {
		AetRestEndpointException exception = Mockito.mock(AetRestEndpointException.class);
		when(exception.getMessage()).thenReturn("Error message");
		Response response = new RestApiExceptionMapper().toResponse(exception);
		OperationStatus entity = (OperationStatus) response.getEntity();
		assertThat(entity.getMessage(), is("Error message"));
		assertThat(entity.isSuccess(), is(false));
	}
}
