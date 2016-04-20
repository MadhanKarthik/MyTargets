/*
 * MyTargets Archery
 *
 * Copyright (C) 2015 Florian Dreier
 * All rights reserved
 */
package de.dreier.mytargets.shared.targets;

import de.dreier.mytargets.shared.R;
import de.dreier.mytargets.shared.utils.Color;

import static android.graphics.Color.BLACK;
import static de.dreier.mytargets.shared.utils.Color.GRAY;
import static de.dreier.mytargets.shared.utils.Color.LIGHTER_GRAY;
import static de.dreier.mytargets.shared.utils.Color.ORANGE;
import static de.dreier.mytargets.shared.utils.Color.TURBO_YELLOW;

public class NFASField extends TargetOvalBase {
    public static final int ID = 22;

    public NFASField() {
        super(ID, R.string.nfas_field);
        zones = 3;
        radius = new float[]{81, 0, 500};
        colorFill = new int[]{TURBO_YELLOW, ORANGE, LIGHTER_GRAY};
        colorStroke = new int[]{BLACK, BLACK, GRAY};
        strokeWidth = new int[]{5, 4, 3};
        zonePoints = new int[][]{{24, 20, 16},{14, 14, 10},{8,8,4}};
        showAsX = new boolean[]{false};
        isFieldTarget = true;
    }
}