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

package org.opennms.netmgt.collection.dto;

import java.util.Objects;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.opennms.netmgt.collection.support.builder.InterfaceLevelResource;

@XmlRootElement(name = "interface-level-resource")
@XmlAccessorType(XmlAccessType.NONE)
public class InterfaceLevelResourceDTO {

    @XmlElement(name = "node-level-resource")
    private NodeLevelResourceDTO parent;

    @XmlAttribute(name = "if-name")
    private String ifName;

    public InterfaceLevelResourceDTO() { }

    public InterfaceLevelResourceDTO(InterfaceLevelResource resource) {
        parent = new NodeLevelResourceDTO(resource.getParent());
        ifName = resource.getIfName();
    }

    @Override
    public String toString() {
        return String.format("InterfaceLevelResourceDTO[parent=%s, ifName=%s]", ifName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(parent, ifName);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj == null) {
            return false;
        } else if (!(obj instanceof InterfaceLevelResourceDTO)) {
            return false;
        }
        InterfaceLevelResourceDTO other = (InterfaceLevelResourceDTO) obj;
        return Objects.equals(this.parent, other.parent)
                && Objects.equals(this.ifName, other.ifName);
    }

    public InterfaceLevelResource toResource() {
        return new InterfaceLevelResource(parent.toResource(), ifName);
    }
}