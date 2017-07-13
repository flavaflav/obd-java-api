/**
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.github.pires.obd.commands.fuel;

import com.github.pires.obd.commands.ObdCommand;
import com.github.pires.obd.enums.AvailableCommandNames;

/**
 * Created by Flavio on 09/07/2017.
 */

public class EthanolPercentageCommand extends ObdCommand {

    private double alcoholRatio = 0.0d;

    /**
     * Default ctor.
     */
    public EthanolPercentageCommand() {
        super("01 52");
    }

    /**
     * Copy ctor
     *
     * @param other a {@link com.github.pires.obd.commands.fuel.FindFuelTypeCommand} object.
     */
    public EthanolPercentageCommand(FindFuelTypeCommand other) {
        super(other);
    }

    /** {@inheritDoc} */
    @Override
    protected void performCalculations() {
        // ignore first two bytes [hh hh] of the response
        double A = buffer.get(2);
        alcoholRatio = (A * 100) / 255; // 100 / 255 * A
    }

    /** {@inheritDoc} */
    @Override
    public String getFormattedResult() {
        try {
            return String.format("%.2f", getAlcoholFuelRatio());
        } catch (NullPointerException e) {
            return "-";
        }
    }

    /** {@inheritDoc} */
    @Override
    public String getCalculatedResult() {
        return String.valueOf(getAlcoholFuelRatio());
    }

    /** {@inheritDoc} */
    @Override
    public String getName() {
        return AvailableCommandNames.FUEL_TYPE.getValue();
    }

    public double getAlcoholFuelRatio() {
        return alcoholRatio;
    }
}
