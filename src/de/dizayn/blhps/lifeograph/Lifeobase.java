/***********************************************************************************

    Copyright (C) 2012-2013 Ahmet Öztürk (aoz_2@yahoo.com)

    This file is part of Lifeograph.

    Lifeograph is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    Lifeograph is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with Lifeograph.  If not, see <http://www.gnu.org/licenses/>.

 ***********************************************************************************/

package de.dizayn.blhps.lifeograph;

import java.util.Locale;

import android.content.Context;

public class Lifeobase {
    public static ActivityOpenDiary activityOpenDiary = null;
    public static ActivityDiary activityDiary = null;
    public static ActivityEntry activityEntry = null;

    public static Context context = null;

    public static final String PROGRAM_NAME = "Lifeograph";

    public static final String LANG_INHERIT_DIARY = "d";

    public static String getStr( int i ) {
        if( context == null )
            return "n/a";
        else
            return context.getString( i );
    }

    public static String get_env_lang() {
        return Locale.getDefault().getLanguage();
    }
}
