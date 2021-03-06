/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package grakn.core.client.concept;

import com.google.auto.value.AutoValue;
import grakn.core.client.GraknClient;
import grakn.core.common.util.CommonUtil;
import grakn.core.graql.concept.Concept;
import grakn.core.graql.concept.ConceptId;
import grakn.core.graql.concept.Rule;
import grakn.core.graql.concept.Type;
import grakn.core.graql.query.pattern.Pattern;
import grakn.core.protocol.ConceptProto;

import javax.annotation.Nullable;
import java.util.stream.Stream;

/**
 * Client implementation of {@link Rule}
 */
@AutoValue
public abstract class RemoteRule extends RemoteSchemaConcept<Rule> implements Rule {

    static RemoteRule construct(GraknClient.Transaction tx, ConceptId id) {
        return new AutoValue_RemoteRule(tx, id);
    }

    @Nullable
    @Override
    public final Pattern when() {
        ConceptProto.Method.Req method = ConceptProto.Method.Req.newBuilder()
                .setRuleWhenReq(ConceptProto.Rule.When.Req.getDefaultInstance()).build();

        ConceptProto.Rule.When.Res response = runMethod(method).getRuleWhenRes();
        switch (response.getResCase()) {
            case NULL:
                return null;
            case PATTERN:
                return Pattern.parse(response.getPattern());
            default:
                throw CommonUtil.unreachableStatement("Unexpected response " + response);
        }
    }

    @Nullable
    @Override
    public final Pattern then() {
        ConceptProto.Method.Req method = ConceptProto.Method.Req.newBuilder()
                .setRuleThenReq(ConceptProto.Rule.Then.Req.getDefaultInstance()).build();

        ConceptProto.Rule.Then.Res response = runMethod(method).getRuleThenRes();
        switch (response.getResCase()) {
            case NULL:
                return null;
            case PATTERN:
                return Pattern.parse(response.getPattern());
            default:
                throw CommonUtil.unreachableStatement("Unexpected response " + response);
        }
    }

    @Override
    public final Stream<Type> whenTypes() {
        throw new UnsupportedOperationException(); // TODO: remove from API
    }

    @Override
    public final Stream<Type> thenTypes() {
        throw new UnsupportedOperationException(); // TODO: remove from API
    }

    @Override
    final Rule asCurrentBaseType(Concept other) {
        return other.asRule();
    }

    @Override
    final boolean equalsCurrentBaseType(Concept other) {
        return other.isRule();
    }
}
