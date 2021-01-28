/*
 * (C) Copyright 2018-2020, by Alexandru Valeanu and Contributors.
 *
 * JGraphT : a free Java graph-theory library
 *
 * See the CONTRIBUTORS.md file distributed with this work for additional
 * information regarding copyright ownership.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0, or the
 * GNU Lesser General Public License v2.1 or later
 * which is available at
 * http://www.gnu.org/licenses/old-licenses/lgpl-2.1-standalone.html.
 *
 * SPDX-License-Identifier: EPL-2.0 OR LGPL-2.1-or-later
 */
package org.jgrapht.alg.spanning;

import org.jgrapht.*;
import org.jgrapht.alg.interfaces.*;
import org.jgrapht.graph.*;

public class BoruvkaMinimumSpanningTreeTest
    extends
    MinimumSpanningTreeTest
{

    @Override
    SpanningTreeAlgorithm<DefaultWeightedEdge> createSolver(
        Graph<Integer, DefaultWeightedEdge> network)
    {
        return new BoruvkaMinimumSpanningTree<>(network);
    }
}