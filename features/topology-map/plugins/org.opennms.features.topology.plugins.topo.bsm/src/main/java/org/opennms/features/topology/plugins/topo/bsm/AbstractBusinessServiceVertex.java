/*******************************************************************************
 * This file is part of OpenNMS(R).
 *
 * Copyright (C) 2012-2016 The OpenNMS Group, Inc.
 * OpenNMS(R) is Copyright (C) 1999-2016 The OpenNMS Group, Inc.
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

package org.opennms.features.topology.plugins.topo.bsm;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.opennms.features.topology.api.topo.AbstractVertex;
import org.opennms.features.topology.api.topo.LevelAware;
import org.opennms.features.topology.api.topo.VertexRef;

abstract class AbstractBusinessServiceVertex extends AbstractVertex implements LevelAware {

    enum Type {
        BusinessService,
        IpService,
        ReductionKey,
    }

    private final List<VertexRef> children = new ArrayList<>();

    private final int level;

    /**
     * Creates a new {@link AbstractBusinessServiceVertex}.
     *
     * @param id the unique id of this vertex. Must be unique overall the namespace.
     * @param label a human readable label
     * @param level the level of the vertex in the Business Service Hierarchy. The root element is level 0.
     */
    protected AbstractBusinessServiceVertex(String id, String label, int level) {
        super(BusinessServicesTopologyProvider.TOPOLOGY_NAMESPACE, id, label);
        this.level = level;
        setIconKey(null);
        setLocked(false);
        setSelected(false);
    }

    public void addChildren(AbstractVertex vertex) {
        if (!children.contains(vertex)) {
            children.add(vertex);
            vertex.setParent(this);
        }
    }

    public boolean isRoot() {
        return getParent() == null;
    }

    public boolean isLeaf() {
        return children.isEmpty();
    }

    public List<VertexRef> getChildren() {
        return children;
    }

    public AbstractBusinessServiceVertex getRoot() {
        if (isRoot()) {
            return this;
        }
        return ((AbstractBusinessServiceVertex) getParent()).getRoot();
    }

    @Override
    public int getLevel() {
        return level;
    }

    public abstract Type getType();

    public abstract Set<String> getReductionKeys();

}
