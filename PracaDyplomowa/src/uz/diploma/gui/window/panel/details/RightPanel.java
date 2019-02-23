package uz.diploma.gui.window.panel.details;

import javax.swing.JComponent;
import javax.swing.JPanel;

import java.awt.Component;
import java.awt.Container;
import java.awt.GridBagLayout;

import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;

import java.awt.GridBagConstraints;
import java.util.ArrayList;

import javax.swing.JScrollPane;

import uz.diploma.model.record.element.Record;
import uz.diploma.tools.Validator;
import uz.diploma.gui.window.panel.details.contexttab.ContextTab;
import uz.diploma.gui.window.panel.details.contexttab.ContextTabView;
import uz.diploma.gui.window.panel.details.overviewtab.OverviewSummary;
import uz.diploma.gui.window.panel.details.overviewtab.OverviewTab;
import uz.diploma.gui.window.panel.details.overviewtab.OverviewTabInterface;
import uz.diploma.gui.window.panel.details.overviewtab.OverviewTableOfContents;
import uz.diploma.gui.window.panel.details.quotationtab.QuotationTabPanel;
import uz.diploma.gui.window.panel.details.quotationtab.QuotationTabView;

import java.awt.Dimension;

/**
 * @author Spider
 * 
 * view class, part of the main window
 * consists of 3 tabs that present various informations about selected record
 *
 */
public class RightPanel extends JPanel implements RightPanelInterface {
	private JTabbedPane rightPanelTabbedPane;
	private QuotationTabView quotationTabPanel;
	private OverviewTabInterface overviewTabPanel;
	private ContextTabView contextPane;

	/**
	 * Create the panel.
	 */
	public RightPanel(OverviewTabInterface overviewTabPanel, ContextTabView contextPane, QuotationTabView quotationTab) {
		Validator.ensureIsNotNull(overviewTabPanel, "overviewTabPanel");
		Validator.ensureIsNotNull(contextPane, "contextPane");
		Validator.ensureIsNotNull(quotationTab, "contextPane");
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JScrollPane rightPanelscrollPane = new JScrollPane();
		GridBagConstraints gbc_rightPanelscrollPane = new GridBagConstraints();
		gbc_rightPanelscrollPane.fill = GridBagConstraints.BOTH;
		gbc_rightPanelscrollPane.gridx = 0;
		gbc_rightPanelscrollPane.gridy = 0;
		add(rightPanelscrollPane, gbc_rightPanelscrollPane);
		
		rightPanelTabbedPane = new JTabbedPane(SwingConstants.TOP);
		rightPanelscrollPane.setViewportView(rightPanelTabbedPane);
		
		this.overviewTabPanel = overviewTabPanel;
		GridBagLayout gridBagLayout_2 = (GridBagLayout) ((JPanel)overviewTabPanel).getLayout();
		gridBagLayout_2.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0};
		gridBagLayout_2.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 1.0};
		rightPanelTabbedPane.addTab("Overview", null, (JPanel)overviewTabPanel, null);
		
		quotationTabPanel = quotationTab;
		((JComponent)quotationTabPanel).setMinimumSize(new Dimension(0, 0));
		rightPanelTabbedPane.addTab("Quotations", null, (Component) quotationTabPanel, null);
		
		this.contextPane = contextPane;
		GridBagLayout gridBagLayout_3 = (GridBagLayout) ((Container) contextPane).getLayout();
		gridBagLayout_3.columnWidths = new int[]{0, 83, 0, 0, 0, 0, 0, 0, 0};
		((JComponent) contextPane).setMinimumSize(new Dimension(0, 0));
		rightPanelTabbedPane.addTab("Context", null, (Component) contextPane, null);

	}

}
