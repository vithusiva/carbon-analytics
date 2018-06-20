/*
 * Copyright (c) 2018, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 * WSO2 Inc. licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.wso2.carbon.siddhi.editor.core.util.designview.utilities;

import org.wso2.carbon.siddhi.editor.core.util.designview.beans.configs.CommentCodeSegment;
import org.wso2.carbon.siddhi.editor.core.util.designview.beans.configs.ElementCodeSegment;
import org.wso2.carbon.siddhi.editor.core.util.designview.exceptions.DesignGenerationException;
import org.wso2.siddhi.query.api.SiddhiElement;

/**
 * Has methods involved in converting Siddhi elements to Design view Config objects
 */
public class ConfigBuildingUtilities {
    /**
     * Avoids Instantiation
     */
    private ConfigBuildingUtilities() {
    }

    /**
     * Gets the piece of the code for the given SiddhiElement, from the siddhiAppString
     * @param siddhiElement                     SiddhiElement object, whose code definition is to be extracted
     * @param siddhiAppString                   Complete Siddhi app string
     * @return                                  Code definition of the given SiddhiElement object
     * @throws DesignGenerationException        Error on getting query start/end index of the element
     */
    public static String getDefinition(SiddhiElement siddhiElement, String siddhiAppString)
            throws DesignGenerationException {
        int[] startIndex = siddhiElement.getQueryContextStartIndex();
        int[] endIndex = siddhiElement.getQueryContextEndIndex();
        return getStringWithQueryContextIndexes(startIndex, endIndex, siddhiAppString);

    }

    // TODO comment
    public static String getComment(CommentCodeSegment commentCodeSegment, String siddhiAppString)
            throws DesignGenerationException {
        return getStringWithQueryContextIndexes(
                commentCodeSegment.getQueryContextStartIndex(),
                commentCodeSegment.getQueryContextEndIndex(),
                siddhiAppString);
    }

    public static String getStringWithQueryContextIndexes(int[] startIndex, int[] endIndex, String siddhiAppString)
            throws DesignGenerationException {
        if (startIndex == null || endIndex == null) {
            throw new DesignGenerationException("Unable to get the definition of the SiddhiElement");
        }

        int startLinePosition = ordinalIndexOf(startIndex[0], siddhiAppString);
        int endLinePosition = ordinalIndexOf(endIndex[0], siddhiAppString);

        return siddhiAppString.substring(startLinePosition + startIndex[1], endLinePosition + endIndex[1]);
    }

    /**
     * Gets the relative position in the siddhiAppString of the start of the given line number.
     *
     * @param lineNumber        The line number in which the relative start position should be obtained
     * @param siddhiAppString   Complete Siddhi app string
     * @return                  The relative position of where the given line starts in the siddhiAppString
     */
    private static int ordinalIndexOf(int lineNumber, String siddhiAppString) {
        int position = 0;
        while (lineNumber >= 0) {
            lineNumber--;
            if (lineNumber <= 0) {
                break;
            }
            position = siddhiAppString.indexOf('\n', position) + 1;
        }
        return position;
    }
}
