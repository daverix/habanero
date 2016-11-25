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
package net.daverix.habanero.sitemaplist;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import net.daverix.habanero.PageOpener;

public class SitemapItemViewModel extends BaseObservable {
    private final PageOpener pageOpener;
    private final String name;
    private final String title;

    public SitemapItemViewModel(String name, String title, PageOpener pageOpener) {
        this.name = name;
        this.title = title;
        this.pageOpener = pageOpener;
    }

    @Bindable
    public String getTitle() {
        return title;
    }

    public void onItemClicked() {
        pageOpener.openPage(name, title);
    }
}
