/*
 * Licensed to Elasticsearch under one or more contributor
 * license agreements. See the NOTICE file distributed with
 * this work for additional information regarding copyright
 * ownership. Elasticsearch licenses this file to you under
 * the Apache License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.elasticsearch.index;

import org.elasticsearch.cluster.ClusterState;
import org.elasticsearch.common.Strings;
import org.elasticsearch.test.ESTestCase;

import static org.apache.lucene.util.TestUtil.randomSimpleString;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.not;

public class IndexTests extends ESTestCase {
    public void testToString() {
        assertEquals("[name/uuid]", new Index("name", "uuid").toString());
        assertEquals("[name]", new Index("name", ClusterState.UNKNOWN_UUID).toString());

        Index random = new Index(randomSimpleString(random(), 1, 100),
                usually() ? Strings.randomBase64UUID(random()) : ClusterState.UNKNOWN_UUID);
        assertThat(random.toString(), containsString(random.getName()));
        if (ClusterState.UNKNOWN_UUID.equals(random.getUUID())) {
            assertThat(random.toString(), not(containsString(random.getUUID())));
        } else {
            assertThat(random.toString(), containsString(random.getUUID()));
        }
    }
}
