package net.runelite.client.plugins.envisionplugins.breakhandler;

import net.runelite.client.plugins.envisionplugins.breakhandler.ui.*;
import net.runelite.client.plugins.envisionplugins.breakhandler.ui.account.AccountParentPanel;
import net.runelite.client.plugins.envisionplugins.breakhandler.ui.breakduration.BreakDurationParentPanel;
import net.runelite.client.plugins.envisionplugins.breakhandler.ui.breakmethod.BreakMethodParentPanel;
import net.runelite.client.plugins.envisionplugins.breakhandler.ui.breaktimer.BreakTimerParentPanel;
import net.runelite.client.plugins.envisionplugins.breakhandler.ui.common.timeamount.MaximumTimeAmount;
import net.runelite.client.plugins.envisionplugins.breakhandler.ui.common.timeamount.MinimumTimeAmount;
import net.runelite.client.plugins.envisionplugins.breakhandler.ui.enums.TimeDurationType;
import net.runelite.client.plugins.envisionplugins.breakhandler.ui.runtimeduration.RuntimeDurationParentPanel;
import net.runelite.client.plugins.envisionplugins.breakhandler.ui.runtimer.RunTimerParentPanel;
import net.runelite.client.plugins.envisionplugins.breakhandler.ui.twofactorauth.TwoFactorAuthParentPanel;
import net.runelite.client.ui.ColorScheme;
import net.runelite.client.ui.PluginPanel;

import javax.inject.Inject;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class BreakHandlerPanel extends PluginPanel {

    @Inject
    BreakHandlerScript breakHandlerScript;

    private final RuntimeDurationParentPanel runtimeDurationParentPanel;
    private final BreakDurationParentPanel breakDurationParentPanel;

    private BreakMethodParentPanel breakMethodParentPanel;
    private final JPanel breakDurationPanel = new JPanel();
    private final JPanel playTimeDurationPanel = new JPanel();
    private final JPanel forceBreakPanel = new JPanel();
    public static final Border BORDER = new LineBorder(ColorScheme.LIGHT_GRAY_COLOR, 0);

//    public static final Border BORDER = new CompoundBorder(
//            BorderFactory.createMatteBorder(2, 2, 2, 2, ColorScheme.LIGHT_GRAY_COLOR),
//            BorderFactory.createLineBorder(ColorScheme.LIGHT_GRAY_COLOR));

    @Inject
    BreakHandlerPanel() {
        super(false);

        BoxLayout boxLayout = new BoxLayout(this, BoxLayout.Y_AXIS);
        setLayout(boxLayout);
        setBorder(new EmptyBorder(6, 6, 6, 6));

        add(new TitlePanel());
        breakMethodParentPanel = new BreakMethodParentPanel();
        add(breakMethodParentPanel);

        breakDurationParentPanel = new BreakDurationParentPanel();
        add(breakDurationParentPanel);

        add(new BreakTimerParentPanel());

        runtimeDurationParentPanel = new RuntimeDurationParentPanel();
        add(runtimeDurationParentPanel);

        add(new RunTimerParentPanel());
        add(new AccountParentPanel());
        add(new TwoFactorAuthParentPanel());
    }

    public RuntimeDurationParentPanel getRuntimeDurationParentPanel() {
        return runtimeDurationParentPanel;
    }

    public BreakDurationParentPanel getBreakDurationParentPanel() {
        return breakDurationParentPanel;
    }

    public MinimumTimeAmount getMinimumTimeAmount(TimeDurationType timeDurationType) throws Exception {
        MinimumTimeAmount minimumTimeAmount;
        switch (timeDurationType) {
            case RUNTIME_DURATION:
                minimumTimeAmount = runtimeDurationParentPanel.getMinimumTimeAmount();
                break;
            case BREAK_DURATION:
                minimumTimeAmount = breakDurationParentPanel.getMinimumTimeAmount();
                break;
            default:
                throw new Exception("Unknown TimeDurationType");
        }
        return minimumTimeAmount;
    }

    public MaximumTimeAmount getMaximumTimeAmount(TimeDurationType timeDurationType) throws Exception {
        MaximumTimeAmount maximumTimeAmount;
        switch (timeDurationType) {
            case RUNTIME_DURATION:
                maximumTimeAmount = runtimeDurationParentPanel.getMaximumTimeAmount();
                break;
            case BREAK_DURATION:
                maximumTimeAmount = breakDurationParentPanel.getMaximumTimeAmount();
                break;
            default:
                throw new Exception("Unknown TimeDurationType");
        }
        return maximumTimeAmount;
    }

    public String getBreakMethod() {
        return BreakMethodParentPanel.getBreakMethod();
    }
}
