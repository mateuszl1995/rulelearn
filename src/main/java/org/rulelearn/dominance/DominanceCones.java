/**
 * Copyright (C) Jerzy Błaszczyński, Marcin Szeląg
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.rulelearn.dominance;

import java.util.Set;
import org.rulelearn.data.InformationTable;
import it.unimi.dsi.fastutil.ints.IntOpenHashSet;
import static org.rulelearn.core.Precondition.notNull;

/**
 * Class for calculation and storage of dominance cones originating in objects of an information table.
 *
 * @author Jerzy Błaszczyński (<a href="mailto:jurek.blaszczynski@cs.put.poznan.pl">jurek.blaszczynski@cs.put.poznan.pl</a>)
 * @author Marcin Szeląg (<a href="mailto:marcin.szelag@cs.put.poznan.pl">marcin.szelag@cs.put.poznan.pl</a>)
 */
public class DominanceCones {
	/**
	 * Array of positive cones w.r.t. (straight) dominance relation D (y D x &lt;=&gt; y dominates x), one for each object x from an information table.
	 * Formally, D^+(x) = {y \in U : y D x}.
	 */
	protected Set<Integer>[] positiveDCones;
	/**
	 * Array of negative cones w.r.t. (straight) dominance relation D (x D y &lt;=&gt; x dominates y), one for each object x from an information table.
	 * Formally, D^-(x) = {y \in U : x D y}.
	 */
	protected Set<Integer>[] negativeDCones; //used to calc lower appx of union "at most"
	/**
	 * Array of positive cones w.r.t. (inverse) dominance relation InvD (x InvD y &lt;=&gt; x is dominated by y), one for each object x from an information table.
	 * Formally, InvD^+(x) = {y \in U : x InvD y}.
	 */
	protected Set<Integer>[] positiveInvDCones; //used to calc lower appx of union "at least"
	/**
	 * Array of negative cones w.r.t. (inverse) dominance relation InvD (y InvD x &lt;=&gt; y is dominated by x), one for each object x from an information table.
	 * Formally, InvD^-(x) = {y \in U : y InvD x}.
	 */
	protected Set<Integer>[] negativeInvDCones;
	
	/**
	 * Number of objects for which cones are calculated and stored.
	 */
	protected int numberOfObjects;
	
	/**
	 * Constructs this object by initializing dominance cones.
	 * 
	 * @param informationTable information table for which dominance cones should be calculated
	 * @throws NullPointerException if given information table is {@code null}
	 */
	@SuppressWarnings("unchecked")
	public DominanceCones(InformationTable informationTable) {
		notNull(informationTable, "Information table for calculation of dominance cones is null.");
		this.numberOfObjects = informationTable.getNumberOfObjects();
		
		this.positiveDCones = (Set<Integer>[]) new Set[this.numberOfObjects];
		this.negativeDCones = (Set<Integer>[]) new Set[this.numberOfObjects];
		this.positiveInvDCones = (Set<Integer>[]) new Set[this.numberOfObjects];
		this.negativeInvDCones = (Set<Integer>[]) new Set[this.numberOfObjects];
	}
	
	/**
	 * Gets number of objects for which cones are calculated and stored.
	 * 
	 * @return number of objects for which cones are calculated and stored
	 */
	public int getNumberOfObjects() {
		return this.numberOfObjects;
	}

	/**
	 * Gets positive cone w.r.t. (straight) dominance relation D (y D x &lt;=&gt; y dominates x),
	 * originating in the object x having given index. Formally, D^+(x) = {y \in U : y D x}.
	 * 
	 * @param objectIndex index of an object x from an information table
	 * @return positive cone w.r.t. (straight) dominance relation D, originating in the object x having given index
	 * 
	 * @throws IndexOutOfBoundsException if given object index is lower than zero or exceeds {@link #getNumberOfObjects()}-1
	 */
	public Set<Integer> getPositiveDCone (int objectIndex) {
		if (this.positiveDCones[objectIndex] == null) {
			this.positiveDCones[objectIndex] = new IntOpenHashSet();
			//TODO
		}
		return this.positiveDCones[objectIndex];
	}
	
	//TODO: further implementation
	
}
