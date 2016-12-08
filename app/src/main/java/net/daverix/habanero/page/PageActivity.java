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

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import net.daverix.habanero.PageOpener;
import net.daverix.habanero.R;

public class PageActivity extends AppCompatActivity implements PageOpener {
    public static final String EXTRA_ID = "name";
    public static final String EXTRA_TITLE = "title";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_page);

        Intent intent = getIntent();
        String pageId = intent.getStringExtra(EXTRA_ID);
        String title = intent.getStringExtra(EXTRA_TITLE);

        setTitle(title);

        if(savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.content_page, PageFragment.newInstance(pageId, title))
                    .commit();
        }
    }

    @Override
    public void openPage(String id, String title) {
        startActivity(this, id, title);
    }

    public static void startActivity(Context context, String id, String title) {
        Intent intent = new Intent(context, PageActivity.class);
        intent.putExtra(EXTRA_ID, id);
        intent.putExtra(EXTRA_TITLE, title);
        context.startActivity(intent);
    }
}
