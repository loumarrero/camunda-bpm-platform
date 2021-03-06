/* Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.camunda.bpm.engine.impl.history;

import org.camunda.bpm.engine.impl.history.event.HistoricProcessInstanceEventEntity;
import org.camunda.bpm.engine.repository.ProcessDefinition;

import java.util.Date;

/**
 * The provider is either invoked on root process instance start or end
 * based on the selected history removal time strategy.
 *
 * @author Tassilo Weidner
 */
public interface HistoryRemovalTimeProvider {

  /**
   * Calculates the removal time of historic entities.
   *
   * START: the removal time is set for each historic entity separately on occurrence (creation).
   *        {@link HistoricProcessInstanceEventEntity#getEndTime()} is {@code null}
   *
   * END:   the removal time is updated simultaneously for all historic entities which belong to
   *        the root process instance when it ends.
   *        {@link HistoricProcessInstanceEventEntity#getEndTime()} is not {@code null}
   *
   * @param historicRootProcessInstance which is either in state running or ended
   * @param processDefinition of the historic root process instance
   * @return the removal time of historic entities
   */
  Date calculateRemovalTime(HistoricProcessInstanceEventEntity historicRootProcessInstance, ProcessDefinition processDefinition);

}
