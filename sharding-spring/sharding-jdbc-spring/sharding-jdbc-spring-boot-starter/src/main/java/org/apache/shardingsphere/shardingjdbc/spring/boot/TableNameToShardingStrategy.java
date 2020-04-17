/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.shardingsphere.shardingjdbc.spring.boot;

import java.util.HashMap;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;
import org.apache.shardingsphere.api.config.sharding.strategy.ShardingStrategyConfiguration;
import org.apache.shardingsphere.api.config.sharding.strategy.StandardShardingStrategyConfiguration;

@Getter
@Setter
public class TableNameToShardingStrategy {
    private Map<String, ShardingStrategyConfiguration> databaseShardingStrategyConf = new HashMap<>();

    private Map<String, ShardingStrategyConfiguration> tableShardingStrategyConf = new HashMap<>();

    private ShardingStrategyConfiguration defaultDatabaseShardingStrategyConfig;

    private ShardingStrategyConfiguration defaultTableShardingStrategyConfig;


    /**
     * 获取数据库分配规则.
     *
     * @param tableName 表名
     * @return 分片规则
     */
    public StandardShardingStrategyConfiguration getDatabaseShardingStrategyConfig(final String tableName) {
        ShardingStrategyConfiguration shardingStrategyConfiguration =
            databaseShardingStrategyConf.get(tableName) == null ? defaultDatabaseShardingStrategyConfig : databaseShardingStrategyConf.get(tableName);
        StandardShardingStrategyConfiguration ret = shardingStrategyConfiguration == null ? null : (StandardShardingStrategyConfiguration) shardingStrategyConfiguration;
        return ret;
    }

    /**
     * 获取表分片规则.
     *
     * @param tableName 表名
     * @return 分片规则
     */
    public StandardShardingStrategyConfiguration getTableShardingStrategyConfig(final String tableName) {
        ShardingStrategyConfiguration shardingStrategyConfiguration = tableShardingStrategyConf.get(tableName) == null ? defaultTableShardingStrategyConfig : tableShardingStrategyConf.get(tableName);
        StandardShardingStrategyConfiguration ret = shardingStrategyConfiguration == null ? null : (StandardShardingStrategyConfiguration) defaultTableShardingStrategyConfig;
        return ret;
    }

}
