/*
 * This file is part of TicTaaC.
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
 *
 * Copyright (c) 2022 Mikhail Rusakovich. All Rights Reserved.
 */
package com.github.rusakovichma.tictaac.model.threatmodel.dataflow;

public enum AuthenticationMethod {
    anonymous,
    credentials,
    basic,
    digest,
    openid,
    ldap,
    ntlm,
    kerberos,
    certificate,
    saml,
    bearer,
    s3,
    radius,
    undefined;

    @Override
    public String toString() {
        return this.name();
    }
}
