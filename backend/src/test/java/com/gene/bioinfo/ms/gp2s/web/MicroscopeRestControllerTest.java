/*
 * Copyright 2018 Genentech Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.gene.bioinfo.ms.gp2s.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gene.bioinfo.ms.gp2s.domain.Microscope;
import com.gene.bioinfo.ms.gp2s.service.MicroscopeService;
import com.gene.bioinfo.ms.gp2s.web.base.BaseRestTCase;
import com.gene.bioinfo.ms.gp2s.web.validator.MicroscopeValidator;
import org.junit.Before;
import org.mockito.Mock;

import javax.annotation.Nullable;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class MicroscopeRestControllerTest extends BaseRestTCase<Microscope> {
    @Mock
    private MicroscopeService service;

    @Mock
    private MicroscopeValidator validator;

    @Override
    public String getUri() {
        return "/api/v1/microscope/";
    }

    @Before
    public void setup() throws Exception {
        when(validator.supports(any())).thenReturn(true);
        initBaseRestTests(new MicroscopeRestController(service, validator), service);
    }

    public void createMicroscope() throws Exception {
        final Microscope microscope = MicroscopeRestControllerITCase.createMicroscope(Microscope.builder());

        final String json = new ObjectMapper().writeValueAsString(microscope);

        getMockMvc().perform(post(URI).contentType(JSON_CONTENT_TYPE).content(json)).andExpect(status().isCreated());

        verify(service, times(1)).createItem(microscope);
    }

    @Override
    public Microscope createEntity(@Nullable Long id, @Nullable String label, @Nullable String slug) {
        return MicroscopeRestControllerITCase.createMicroscope(Microscope.builder().id(id).label(label).slug(slug));
    }
}
