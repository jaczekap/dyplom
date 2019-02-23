package uz.diploma;

import java.awt.Desktop;
import java.awt.EventQueue;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;












import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;












import testData.DataTestFiller;
import uz.diploma.controller.DataStoreEventsController;
import uz.diploma.controller.AppWindowController;
import uz.diploma.controller.LeftPaneController;
import uz.diploma.controller.context.CharactersPanelController;
import uz.diploma.controller.context.ContextTabController;
import uz.diploma.controller.context.EventsPanelController;
import uz.diploma.controller.context.KeyWordsPanelController;
import uz.diploma.controller.context.LocationsPanelController;
import uz.diploma.controller.overview.OverviewTabController;
import uz.diploma.controller.overview.SummaryPanelController;
import uz.diploma.controller.overview.TableOfContentsPanelController;
import uz.diploma.controller.quotation.QuotationTabController;
import uz.diploma.gui.window.AppWindow;
import uz.diploma.gui.window.WindowView;
import uz.diploma.gui.window.panel.details.RightPanel;
import uz.diploma.gui.window.panel.details.RightPanelInterface;
import uz.diploma.gui.window.panel.details.contexttab.ContextTab;
import uz.diploma.gui.window.panel.details.contexttab.ContextTabView;
import uz.diploma.gui.window.panel.details.contexttab.panel.CharactersPanel;
import uz.diploma.gui.window.panel.details.contexttab.panel.EventsPanel;
import uz.diploma.gui.window.panel.details.contexttab.panel.KeyWordsPanel;
import uz.diploma.gui.window.panel.details.contexttab.panel.LocationsPanel;
import uz.diploma.gui.window.panel.details.contexttab.panel.PanelView;
import uz.diploma.gui.window.panel.details.overviewtab.OverviewSummary;
import uz.diploma.gui.window.panel.details.overviewtab.OverviewTab;
import uz.diploma.gui.window.panel.details.overviewtab.OverviewTabInterface;
import uz.diploma.gui.window.panel.details.overviewtab.OverviewTableOfContents;
import uz.diploma.gui.window.panel.details.overviewtab.SummaryPanelInterface;
import uz.diploma.gui.window.panel.details.overviewtab.TableOfContentsInterface;
import uz.diploma.gui.window.panel.details.quotationtab.QuotationTabPanel;
import uz.diploma.gui.window.panel.details.quotationtab.QuotationTabView;
import uz.diploma.gui.window.panel.record.RecordsPanel;
import uz.diploma.gui.window.panel.record.RecordsView;
import uz.diploma.listeners.DataStoreEventsListener;
import uz.diploma.listeners.LoadEventListener;
import uz.diploma.listeners.TextFieldChangedListener;
import uz.diploma.model.project.*;
import uz.diploma.model.record.element.HistoricalCharacter;
import uz.diploma.model.record.source.SourceLocation;

/**
 * @author Spider
 * 
 * Class that prepared all object needed to initialize one instance of main window
 *
 */
public class WindowBuilder implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5313740892739580952L;
	private DataTestFiller testFiller;
	private Project project;
	private DataStoreEventsManagerInterface projectDataStoreEventsManager;
	private SummaryPanelInterface summaryPanel;
	private TableOfContentsInterface tableOfContentsPanel;
	private OverviewTabInterface overviewTab;
	private ContextTabView contextPane;
	private RecordsView leftPanel;
	private RightPanelInterface rightPanel;
	private WindowView appWindow;
	private SummaryPanelController summaryPanelController;
	private TableOfContentsPanelController tableOfContentsPanelController;
	private OverviewTabController overviewTabController;
	private ContextTabController contextTabController;
	private LeftPaneController panelsController;
	private DataStoreEventsListener storeEventController;
	private AppWindowController mainWindowController;
	private PanelView charactersPanel;
	private PanelView eventsPanel;
	private PanelView keyWordsPanel;
	private PanelView locationsPanel;
	private CharactersPanelController characterPanelController;
	private EventsPanelController eventsPanelController;
	private KeyWordsPanelController keyWordsPanelController;
	private LocationsPanelController locationsPanelController;
	private QuotationTabView quotationTab;
	private QuotationTabController quotationTabController;
	
	public void build() {
		 try {
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			} catch (ClassNotFoundException | InstantiationException
					| IllegalAccessException | UnsupportedLookAndFeelException e1) {
				e1.printStackTrace();
			}
		 
		EventQueue.invokeLater(() -> {
			try {
				testFiller = new DataTestFiller();
				project = testFiller.getProject();
				//project = new Project();
				projectDataStoreEventsManager = new DataStoreEventsManager(project);
				
				buildAppWindow();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}
	
	private void buildAppWindow() {
		leftPanel = new RecordsPanel();
		buildRightPanel();
		panelsController = new LeftPaneController(leftPanel, project);
		projectDataStoreEventsManager.addLoadEventListener(panelsController);
		appWindow = new AppWindow(leftPanel, rightPanel);
		storeEventController = new DataStoreEventsController(appWindow, projectDataStoreEventsManager);
		mainWindowController = new AppWindowController(project, appWindow);
		leftPanel.addResultListSelectedListener(mainWindowController);
		mainWindowController.addListener(panelsController);
		projectDataStoreEventsManager.addLoadEventListener(mainWindowController);
	}
	
	private void buildContextTab() {
		charactersPanel = new CharactersPanel();
		characterPanelController = new CharactersPanelController(charactersPanel, project);
		leftPanel.addResultListSelectedListener(characterPanelController);
		projectDataStoreEventsManager.addLoadEventListener(characterPanelController);
		
		eventsPanel = new EventsPanel();
		eventsPanelController = new EventsPanelController(eventsPanel, project);
		leftPanel.addResultListSelectedListener(eventsPanelController);
		projectDataStoreEventsManager.addLoadEventListener(eventsPanelController);
		
		keyWordsPanel = new KeyWordsPanel();
		keyWordsPanelController = new KeyWordsPanelController(keyWordsPanel, project);
		leftPanel.addResultListSelectedListener(keyWordsPanelController);
		projectDataStoreEventsManager.addLoadEventListener(keyWordsPanelController);
		
		locationsPanel = new LocationsPanel();
		locationsPanelController = new LocationsPanelController(locationsPanel, project);
		leftPanel.addResultListSelectedListener(locationsPanelController);
		projectDataStoreEventsManager.addLoadEventListener(locationsPanelController);
		
		contextPane = new ContextTab(charactersPanel, eventsPanel, locationsPanel, keyWordsPanel);
		contextTabController = new ContextTabController(contextPane);
		leftPanel.addResultListSelectedListener(contextTabController);
		
		contextPane.addFilterListener(characterPanelController);
		contextPane.addFilterListener(eventsPanelController);
		contextPane.addFilterListener(locationsPanelController);
		contextPane.addFilterListener(keyWordsPanelController);
	}
	
	private void buildOverviewTab() {
		summaryPanel = new OverviewSummary();
		summaryPanelController = new SummaryPanelController(summaryPanel);
		leftPanel.addResultListSelectedListener(summaryPanelController);
		
		tableOfContentsPanel = new OverviewTableOfContents();
		tableOfContentsPanelController = new TableOfContentsPanelController(tableOfContentsPanel);
		leftPanel.addResultListSelectedListener(tableOfContentsPanelController);
		
		overviewTab = new OverviewTab(summaryPanel, tableOfContentsPanel);
		overviewTabController = new OverviewTabController(overviewTab, project);
		leftPanel.addResultListSelectedListener(overviewTabController);
	}
	
	private void buildQuotationTab() {
		quotationTab = new QuotationTabPanel();
		quotationTabController = new QuotationTabController(quotationTab, project);
		leftPanel.addResultListSelectedListener(quotationTabController);
		projectDataStoreEventsManager.addLoadEventListener(quotationTabController);
	}
	
	private void buildRightPanel() {
		buildOverviewTab();
		buildContextTab();
		buildQuotationTab();
		rightPanel = new RightPanel(overviewTab, contextPane, quotationTab);
	}
}
