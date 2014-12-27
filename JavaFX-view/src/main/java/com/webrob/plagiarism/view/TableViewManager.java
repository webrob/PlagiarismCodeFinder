package com.webrob.plagiarism.view;

import com.webrob.plagiarism.domain.PlagiarismDataForTableView;
import com.webrob.plagiarism.domain.RowSummary;
import javafx.application.Platform;
import javafx.collections.ObservableList;

import java.util.List;

/**
 * Created by Robert on 2014-12-26.
 */
public class TableViewManager
{
    private final ObservableList<PlagiarismDataForTableView> plagiarisms;

    public TableViewManager(ObservableList<PlagiarismDataForTableView> plagiarisms)
    {
        this.plagiarisms = plagiarisms;
    }

    public void setDataForTableView(List<RowSummary> rowsSummary)
    {
        Platform.runLater(() ->
        {
            plagiarisms.clear();
            for (RowSummary rowSummary: rowsSummary)
            {

                PlagiarismDataForTableView dataForTableView = new PlagiarismDataForTableView(
                                rowSummary.getFirstFile(), rowSummary.getSecondFile());
                plagiarisms.add(dataForTableView);
            }
        });
    }
}
