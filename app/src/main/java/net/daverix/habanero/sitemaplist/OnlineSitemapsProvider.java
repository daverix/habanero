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

import net.daverix.habanero.PageOpener;
import net.daverix.habanero.rest.OpenHabService;

import javax.inject.Inject;

import io.reactivex.Observable;

public class OnlineSitemapsProvider implements SitemapsProvider {
    private final OpenHabService openHabService;
    private final PageOpener pageOpener;

    @Inject
    public OnlineSitemapsProvider(OpenHabService openHabService, PageOpener pageOpener) {
        this.openHabService = openHabService;
        this.pageOpener = pageOpener;
    }

    @Override
    public Observable<SitemapViewModel> getSitemaps() {
        return openHabService.getSitemaps()
                .flatMap(Observable::fromIterable)
                .map(x -> new SitemapViewModel(pageOpener, x));
    }
}
