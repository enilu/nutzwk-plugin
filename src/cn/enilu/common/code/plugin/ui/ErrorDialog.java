package cn.enilu.common.code.plugin.ui;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

/**
 * Created by ghost on 2016/4/1.
 */
public class ErrorDialog extends DialogWrapper {
    public ErrorDialog(@Nullable Project project) {
        super(project);
        setTitle("Error Occured, Please Retry!");
        setOKActionEnabled(false);
        init();
    }

    @Nullable
    @Override
    protected JComponent createCenterPanel() {
        return null;
    }
}
