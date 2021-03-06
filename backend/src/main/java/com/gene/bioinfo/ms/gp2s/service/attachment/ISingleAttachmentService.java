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

package com.gene.bioinfo.ms.gp2s.service.attachment;

import lombok.NonNull;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * Allow to upload file without linking it with eny entity.
 * Then entity should be updated to have reference to file otherwise file will be orphan
 */
public interface ISingleAttachmentService {

    @NonNull
    UploadResponse createFile(final MultipartFile file);

    @NonNull
    File getFile(final String fileId) throws IOException;

    void deleteFile(final String fileId);
}
