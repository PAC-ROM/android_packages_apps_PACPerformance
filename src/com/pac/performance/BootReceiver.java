package com.pac.performance;

import com.pac.performance.utils.Constants;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class BootReceiver extends BroadcastReceiver implements Constants {

    @Override
    public void onReceive(Context context, Intent intent) {

        if (rootHelper.rootAccess() && rootHelper.busyboxInstalled())

        if (mUtils.getBoolean("setonboot", false, context)) {
            String savedCommands = mUtils.getString(COMMAND_NAME, "", context);

            if (!savedCommands.isEmpty()) {
                String[] files = savedCommands.split(mCommandControl.fileSplit);
                for (String file : files)
                    rootHelper
                            .runCommand(mUtils.getString(file, "ls", context));
            }

            mUtils.toast(
                    context.getString(R.string.booted_up,
                            context.getString(R.string.app_name)), context);
        }

        if (mUtils.getBoolean("customcommander", false, context)) {
            String savedCommands = mUtils.getString(
                    mCustomCommanderFragment.prefName, "", context);

            if (!savedCommands.isEmpty()) {
                for (String command : mCustomCommanderFragment
                        .getSavedCommands(savedCommands))
                    rootHelper.runCommand(command);
            }

            mUtils.toast(
                    context.getString(R.string.booted_up,
                            context.getString(R.string.custom_commander)),
                    context);
        }

        return;
    }
}
