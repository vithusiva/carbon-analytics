
/*
 * Copyright (c) 2005-2013, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 *  WSO2 Inc. licenses this file to you under the Apache License,
 *  Version 2.0 (the "License"); you may not use this file except
 *  in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 */

package org.wso2.carbon.event.processor.api.receive;

import org.wso2.carbon.databridge.commons.Attribute;

/**
 *
 */
public interface BasicEventListener {

    /**
     * This method will be triggered for all listeners whenever an event is received
     *
     * @param eventData the event object which will be an {@link Object[]}
     */
    public void onEvent(Object[] eventData);

    /**
     * This method will be triggered when a new stream definition is added.
     * This method will be triggered only when the input events are of type WSO2Event
     *
     * @param definitionAttributes the stream definition.
     */
    public void onAddDefinition(Attribute[] definitionAttributes);

    /**
     * This method will be triggered when a new stream definition is removed.
     * This method will be triggered only when the input events are of type WSO2Event
     *
     * @param definitionAttributes the stream definition.
     */
    public void onRemoveDefinition(Attribute[] definitionAttributes);

}
