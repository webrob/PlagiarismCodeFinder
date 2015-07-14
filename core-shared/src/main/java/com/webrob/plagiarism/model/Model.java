package com.webrob.plagiarism.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public abstract class Model
{
    protected PropertyChangeSupport propertyChangeSupport;

    public Model() {
        propertyChangeSupport = new PropertyChangeSupport(this);
    }
    
    public void addPropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.addPropertyChangeListener(l);
    }

    protected void firePropertyChange(String propertyName, Object newValue) {
        propertyChangeSupport.firePropertyChange(propertyName, null, newValue);
    }
        
    public abstract void calculate();

    public abstract void setFilePaths(String directoryPath);

    public abstract void fireSourceFiles(int selectedIndex);

    public abstract void setMaxLineGapValue(int value);

    public abstract void setMinChainLengthValue(int value);
}
    

