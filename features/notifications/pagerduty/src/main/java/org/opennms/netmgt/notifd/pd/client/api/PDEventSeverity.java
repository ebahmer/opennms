/*******************************************************************************
 * This file is part of OpenNMS(R).
 *
 * Copyright (C) 2017-2017 The OpenNMS Group, Inc.
 * OpenNMS(R) is Copyright (C) 1999-2017 The OpenNMS Group, Inc.
 *
 * OpenNMS(R) is a registered trademark of The OpenNMS Group, Inc.
 *
 * OpenNMS(R) is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published
 * by the Free Software Foundation, either version 3 of the License,
 * or (at your option) any later version.
 *
 * OpenNMS(R) is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with OpenNMS(R).  If not, see:
 *      http://www.gnu.org/licenses/
 *
 * For more information contact:
 *     OpenNMS(R) Licensing <license@opennms.org>
 *     http://www.opennms.org/
 *     http://www.opennms.com/
 *******************************************************************************/

package org.opennms.netmgt.notifd.pd.client.api;

import org.opennms.netmgt.model.OnmsSeverity;

public enum PDEventSeverity {
    CRITICAL,
    ERROR,
    WARNING,
    INFO;

    public static PDEventSeverity fromOnmsSeverity(OnmsSeverity severity) {
        switch(severity) {
            case INDETERMINATE:
                return INFO;
            case CLEARED:
                return INFO;
            case NORMAL:
                return INFO;
            case WARNING:
                return WARNING;
            case MINOR:
                return ERROR;
            case MAJOR:
                return ERROR;
            case CRITICAL:
                return CRITICAL;
        }
        return CRITICAL;
    }
}
