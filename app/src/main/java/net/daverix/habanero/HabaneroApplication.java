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
package net.daverix.habanero;

import android.app.Application;

import net.daverix.habanero.page.PageComponent;
import net.daverix.habanero.rest.RestModule;
import net.daverix.habanero.sitemaplist.SitemapListComponent;

import javax.inject.Singleton;

import dagger.Component;

public class HabaneroApplication extends Application {
    private AppComponent appComponent;

    public AppComponent appComponent() {
        if(appComponent == null)
            appComponent = DaggerHabaneroApplication_AppComponent.builder().build();

        return appComponent;
    }

    @Singleton
    @Component(modules = {
            RestModule.class
    })
    public interface AppComponent {
        SitemapListComponent.Builder sitemapComponentBuilder();
        PageComponent.Builder pageComponentBuilder();
    }
}
