package org.example.javabeans;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class DirectoryObserver {
    private int imageCount;
    private final PropertyChangeSupport support;

    public DirectoryObserver() {
        support = new PropertyChangeSupport(this);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        support.removePropertyChangeListener(listener);
    }

    public void setImageCount(int newCount) {
        int oldCount = this.imageCount;
        this.imageCount = newCount;
        support.firePropertyChange("imageCount", oldCount, newCount);
    }
}
