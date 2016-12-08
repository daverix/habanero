/*
    Standalone Android Client for OpenHAB server
    Copyright (C) 2016  David Laurell

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/
package net.daverix.habanero.page;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import net.daverix.habanero.PageOpener;

public class TitleWidgetItemViewModel extends BaseObservable implements WidgetViewModel {
    private final String type;
    private final String widgetId;
    private final String pageId;
    private final String title;
    private final String icon;
    private final PageOpener pageOpener;

    public TitleWidgetItemViewModel(String type,
                                    String widgetId,
                                    String pageId,
                                    String title,
                                    String icon,
                                    PageOpener pageOpener) {
        this.widgetId = widgetId;
        this.type = type;
        this.pageId = pageId;
        this.title = title;
        this.icon = icon;
        this.pageOpener = pageOpener;
    }

    @Override
    public long getId() {
        return widgetId.hashCode();
    }

    @Override
    public int getViewType() {
        return type.hashCode();
    }

    @Bindable
    public String getTitle() {
        return title;
    }

    @Bindable
    public String getIcon() {
        return icon;
    }

    public void onItemClicked() {
        if(pageOpener != null && pageId != null) {
            pageOpener.openPage(pageId, title);
        }
    }
}
