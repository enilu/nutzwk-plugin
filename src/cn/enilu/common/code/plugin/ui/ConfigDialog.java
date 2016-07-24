package cn.enilu.common.code.plugin.ui;

import cn.enilu.common.code.plugin.utils.GenerateConfig;
import com.intellij.ide.util.DefaultPsiElementCellRenderer;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.openapi.ui.LabeledComponent;
import com.intellij.psi.PsiAnnotation;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiField;
import com.intellij.psi.PsiModifier;
import com.intellij.ui.CollectionListModel;
import com.intellij.ui.ToolbarDecorator;
import com.intellij.ui.components.JBCheckBox;
import com.intellij.ui.components.JBList;
import com.intellij.ui.components.JBTextField;
import com.intellij.ui.components.panels.VerticalBox;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Created by ghost on 2016/4/1.
 */
public class ConfigDialog extends DialogWrapper {
    private JBCheckBox controllerCheckBox;
    private JBCheckBox serviceCheckBox;
    private JBCheckBox viewCheckBox;
    private JBTextField baseUriTextField;
    private JBTextField basePackageTextField;
    private final PsiClass mClass;
    private final String basePackage;
    private final String baseUri;

    public ConfigDialog(final PsiClass psiClass) {
        super(psiClass.getProject());
        String arr[] = psiClass.getQualifiedName().split("\\.");
        String modelName = psiClass.getName();

        basePackage = psiClass.getQualifiedName().replace("."+arr[arr.length-2]+"."+arr[arr.length-1],"");
        baseUri = "/private/"+basePackage.substring(basePackage.lastIndexOf(".")+1);
        mClass = psiClass;
        setupViews(modelName);
        init();
    }


    private void setupViews(String modelName) {

        setTitle("Generate Model:"+modelName);

        controllerCheckBox = new JBCheckBox("controllers",true);
        serviceCheckBox = new JBCheckBox("services",true);
        viewCheckBox = new JBCheckBox("views",true);

        baseUriTextField = new JBTextField(baseUri);
        basePackageTextField = new JBTextField(basePackage);
    }

    @Nullable
    @Override
    protected JComponent createSouthPanel() {
        JComponent southPanel = super.createSouthPanel();
        if (null == southPanel) {
            return null;
        }
        final VerticalBox root = new VerticalBox();
        root.add(baseUriTextField);
        root.add(basePackageTextField);
        root.add(southPanel);
        return root;
    }

    @Nullable
    @Override
    protected JComponent createCenterPanel() {
        JComponent centerPanel = super.createContentPane();
        centerPanel.add(controllerCheckBox);
        centerPanel.add(serviceCheckBox);
        centerPanel.add(viewCheckBox);
        return centerPanel;
    }

    public GenerateConfig getGenerateConfig(){
        GenerateConfig config = new GenerateConfig();
        config.setBasePackage(basePackage);
        config.setBaseUri(baseUriTextField.getText().trim());
        config.setConroller(controllerCheckBox.isBorderPainted());
        config.setService(serviceCheckBox.isBorderPainted());
        config.setView(viewCheckBox.isBorderPainted());
        return config;
    }

}
