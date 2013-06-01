package edu.uoregon.stockdb.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;

/**
 */
public class ExperimentTable extends FlexTable {

    int numberRows = 0;

    private final int STRAIN_COLUMN = 0;
    private final int VALUE_COLUMN = 1;
    private final int CATEGORY_COLUMN = 2;
    private final int ACTION_COLUMN = 3;

    private final String STRAIN_KEY = "strain";
    private final String VALUE_KEY = "value";
    private final String CATEGORY_KEY = "category";
    private final String EXPERIMENTS_KEY = "experiments";
    private final String CATEGORIES_KEY = "categories";
    private final String STRAINS_KEY = "strains";
    private final String EXPERIMENT_KEY = "experimentId";
    private final String MEASURED_VALUE_KEY = "id";

    private Button addButton = new Button("Add");
    private ListBox strainList = new ListBox();
    private TextBox valueBox = new TextBox();
    private ListBox categoryList = new ListBox();

    private Integer experimentId = 0 ;

    public final static String ROW_HEIGHT = "20px";
    MultiWordSuggestOracle strainOracle = new MultiWordSuggestOracle();
    MultiWordSuggestOracle categoryOracle = new MultiWordSuggestOracle();

    private static QuickEntryServiceAsync quickEntryServiceAsync = GWT.create(QuickEntryService.class);

    public ExperimentTable() {
        setCellPadding(0);
        setCellSpacing(0);
//        addStyleName("quick-entry-table");
        setStylePrimaryName("quick-entry-table");
        setWidth("70%");


    }

    public void udpateTable(JSONValue value) {
        clear();

        createHeaders();

        JSONObject measuredValueDto = value.isObject();

        experimentId = (int) measuredValueDto.get(EXPERIMENT_KEY).isNumber().doubleValue();

        JSONArray strains = measuredValueDto.get(STRAINS_KEY).isArray();
        for (int i = 0; i < strains.size(); i++) {
            strainList.addItem(strains.get(i).isString().stringValue());
            strainOracle.add(strains.get(i).isString().stringValue());
        }


        JSONArray categories = measuredValueDto.get(CATEGORIES_KEY).isArray();
        for (int i = 0; i < categories.size(); i++) {
            categoryList.addItem(categories.get(i).isString().stringValue());
            categoryOracle.add(categories.get(i).isString().stringValue());
        }

        JSONArray experiments = measuredValueDto.get(EXPERIMENTS_KEY).isArray();

        GWT.log("number of experiments: " + experiments.size());

        for (int i = 0; i < experiments.size(); i++) {
            JSONObject measuredValue = experiments.get(i).isObject();

            createRow(numberRows
                    , measuredValue.get(STRAIN_KEY).isString().stringValue()
                    , measuredValue.get(VALUE_KEY).isString().stringValue()
                    , measuredValue.get(CATEGORY_KEY).isString().stringValue()
                    , String.valueOf(measuredValue.get(MEASURED_VALUE_KEY).isNumber().doubleValue())
            );

            ++numberRows;
        }


        createFooters();

        // for all cells set style name
        for(int col = 0 ; col < 4 ; col++){
            for(int row = 0 ; row < getRowCount() ; ++row){
                getCellFormatter().setStyleName(row,col,"flexTable");
            }
        }

    }



//        ListBox strainList = new ListBox();
//        int selectedStrainIndex = 0 ;
//        for(int i = 0 ; i < this.strainList.getItemCount() ; i++){
//            strainList.addItem(this.strainList.getItemText(i));
//            if(this.strainList.getItemText(i).equals(strain)){
//                selectedStrainIndex=i ;
//            }
//        }
//        strainList.setSelectedIndex(selectedStrainIndex);

//        setWidget(numberRows, STRAIN_COLUMN, strainList);


    private void createRow(int numberRows, String strain, String value, String category, String measuredValueId) {
//        TextBox strainBox = new TextBox();
        StrainEditBox strainEditBox = new StrainEditBox(strainOracle,Double.valueOf(measuredValueId).intValue(),strain) ;
        setWidget(numberRows, STRAIN_COLUMN, strainEditBox);

//        TextBox valueBox = new TextBox();
//        valueBox.setText(value);
//        valueBox.setHeight(ROW_HEIGHT);
//        valueBox.setStylePrimaryName("quick-entry-table");

        ValueEditBox valueEditBox = new ValueEditBox(Double.valueOf(measuredValueId).intValue(),value);
        setWidget(numberRows, VALUE_COLUMN, valueEditBox);



//        TextBox categoryBox = new TextBox();
//        categoryBox.setText(category);
//        categoryBox.setHeight(ROW_HEIGHT);
//        categoryBox.setStylePrimaryName("quick-entry-table");
//        setWidget(numberRows, CATEGORY_COLUMN, categoryBox);
        ListBox newCategoryList = new ListBox();
        int selectedCategoryIndex = 0 ;
        for(int i = 0 ; i < this.categoryList.getItemCount() ; i++){
            newCategoryList.addItem(this.categoryList.getItemText(i));
            if(this.categoryList.getItemText(i).equals(category)){
                selectedCategoryIndex=i ;
            }
        }
        newCategoryList.setSelectedIndex(selectedCategoryIndex);

        setWidget(numberRows, CATEGORY_COLUMN, newCategoryList);
//        CategoryListBox categoryListBox = new CategoryListBox(Double.valueOf(measuredValueId).intValue(),this.categoryList,category);

        RemoveRowButton removeButton = new RemoveRowButton(numberRows, Double.valueOf(measuredValueId).intValue(),this);
        removeButton.setHeight(ROW_HEIGHT);
        removeButton.setStylePrimaryName("quick-entry-table");

        setWidget(numberRows, ACTION_COLUMN, removeButton);
        getRowFormatter().setStylePrimaryName(numberRows,"quick-entry-table");


    }

    private void addNewRow(String measuredValueId) {
//        GWT.log("row count : " + getRowCount()) ;
        insertRow(getRowCount() - 1);
//        GWT.log("2 row count : " + getRowCount()) ;
        int insertRow = getRowCount() - 2;

//
        createRow(insertRow,
                strainList.getItemText(strainList.getSelectedIndex())
                , valueBox.getText()
                , categoryList.getItemText(categoryList.getSelectedIndex()),
                measuredValueId
        );

        for(int col = 0 ; col < ACTION_COLUMN ; col++){
//            for(int row = 0 ; row < getRowCount() ; ++row){
//                getCellFormatter().setStyleName(row,col,"flexTable");
            getCellFormatter().setStyleName(insertRow,col,"flexTable");
//            }
        }

        ++numberRows;
    }

    private void clearNewEntry() {
        strainList.setSelectedIndex(0);
        categoryList.setSelectedIndex(0);
        valueBox.setText("");
    }

    private void createFooters() {
        setWidget(numberRows, STRAIN_COLUMN, strainList);
        setWidget(numberRows, VALUE_COLUMN, valueBox);
        setWidget(numberRows, CATEGORY_COLUMN, categoryList);
        setWidget(numberRows, ACTION_COLUMN, addButton);

        strainList.insertItem("- Choose Strain - ",0);
        strainList.setSelectedIndex(0);
        categoryList.insertItem("- Choose Category - ",0);
        categoryList.setSelectedIndex(0);

        addButton.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                final String strain = strainList.getItemText(strainList.getSelectedIndex());
                final String value = valueBox.getText();
                final String category = categoryList.getItemText(categoryList.getSelectedIndex());
                quickEntryServiceAsync.createMeasuredValue(experimentId,strain,value,category,new AsyncCallback(){
                    public void onFailure(Throwable caught) {
                        Window.alert("failed to create record  "+strain+" "+value+" "+category);
                    }

                    public void onSuccess(Object result) {
                        addNewRow(result.toString());
                        clearNewEntry();
                    }
                });
            }


        });

        ++numberRows;
    }

    private void createHeaders() {
        setHTML(numberRows, STRAIN_COLUMN, "<b>Strain</b>");
        setHTML(numberRows, VALUE_COLUMN, "<b>Value</b>");
        setHTML(numberRows, CATEGORY_COLUMN, "<b>Category</b>");
        setHTML(numberRows, ACTION_COLUMN, "<b>Action</b>");
        ++numberRows;
    }


    public int findRowId(int rowId) {
        for (int i = 1; i < getRowCount() - 1; i++) {
            if (rowId == ((RemoveRowButton) getWidget(i, ACTION_COLUMN)).getRowId()) {
                return i;
            }
        }
        return -1;
    }
}
