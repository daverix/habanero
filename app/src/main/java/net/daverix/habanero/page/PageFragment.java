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

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.daverix.habanero.ActivityModule;
import net.daverix.habanero.HabaneroApplication;
import net.daverix.habanero.PageOpener;
import net.daverix.habanero.PageOpenerModule;
import net.daverix.habanero.R;
import net.daverix.habanero.databinding.FragmentPageBinding;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class PageFragment extends Fragment {
    private static final String ARG_PAGE_ID = "name";
    private static final String ARG_TITLE = "title";

    @Inject
    WidgetsProvider widgetsProvider;
    @Inject
    WidgetListAdapter adapter;
    private Disposable widgetsDisposable;

    private String name;
    private String title;

    public static PageFragment newInstance(String pageId, String title) {
        if (pageId == null)
            throw new IllegalArgumentException("name is null");
        if (title == null)
            throw new IllegalArgumentException("title is null");

        PageFragment fragment = new PageFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PAGE_ID, pageId);
        args.putString(ARG_TITLE, title);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle args = getArguments();
        if (args == null) throw new IllegalArgumentException("arguments not set");

        name = args.getString(ARG_PAGE_ID);
        title = args.getString(ARG_TITLE);

        ((HabaneroApplication) getActivity().getApplication())
                .appComponent()
                .pageComponentBuilder()
                .activityModule(new ActivityModule(getActivity()))
                .pageOpenerModule(new PageOpenerModule((PageOpener) getActivity()))
                .build()
                .inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentPageBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_page, container, false);
        binding.pages.setAdapter(adapter);
        binding.pages.setLayoutManager(new LinearLayoutManager(getContext()));
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        widgetsDisposable = widgetsProvider.getWidgets(name)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(adapter::addItem);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if (widgetsDisposable != null)
            widgetsDisposable.dispose();
    }
}
