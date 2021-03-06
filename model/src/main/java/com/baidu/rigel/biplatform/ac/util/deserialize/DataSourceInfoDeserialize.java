/**
 * Copyright (c) 2014 Baidu, Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.baidu.rigel.biplatform.ac.util.deserialize;

import java.lang.reflect.Type;

import com.baidu.rigel.biplatform.ac.query.MiniCubeConnection.DataSourceType;
import com.baidu.rigel.biplatform.ac.query.data.DataSourceInfo;
import com.baidu.rigel.biplatform.ac.query.data.impl.SqlDataSourceInfo;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

/**
 * 数据源信息接口反序列化
 * 
 * @author xiaoming.chen
 *
 */
public class DataSourceInfoDeserialize implements JsonDeserializer<DataSourceInfo> {

    @Override
    public DataSourceInfo deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
        if (json.isJsonObject()) {
            DataSourceType dataSourceType =
                    context.deserialize(json.getAsJsonObject().get("dataSourceType"), DataSourceType.class);
            if (dataSourceType.equals(DataSourceType.SQL)
                    || dataSourceType.equals(DataSourceType.ASYN)) {
                return context.deserialize(json, SqlDataSourceInfo.class);
            }

        }
        return null;
    }

}
