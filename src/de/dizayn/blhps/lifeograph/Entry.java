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

import java.util.ArrayList;

public class Entry extends DiaryElement {
    public Entry( Diary diary, Long date, String text, boolean flag_favored ) {
        super( diary, "" );
        m_date = new Date( date );

        // java.util.Date jd = new java.util.Date();
        // m_date_created = jd.getTime() / 1000;
        m_date_created = ( System.currentTimeMillis() / 1000L );
        m_date_changed = m_date_created;

        m_option_favored = flag_favored;
        m_text = new String( text );
        m_ptr2theme = null;
        calculate_title( text );
    }

    public Entry( Diary diary, Long date, boolean flag_favored ) {
        super( diary, Lifeobase.getStr( R.string.empty_entry ) );
        m_date = new Date( date );

        java.util.Date jd = new java.util.Date();
        m_date_created = jd.getTime() / 1000;
        m_date_changed = m_date_created;

        m_option_favored = flag_favored;
        m_ptr2theme = null;
    }

    @Override
    public Type get_type() {
        return Type.ENTRY;
    }

    @Override
    public int get_size() {
        return m_text.length();
    }

    public String getHeadStr() {
        return( m_date.is_ordinal() ? getListStr() : m_date.format_string( true ) );
    }

    @Override
    public String getSubStr() {
        return Lifeobase.activityDiary.getString( R.string.entry_last_changed_on ) + " "
               + Date.format_string_do( m_date_changed );
    }

    @Override
    public String getListStr() {
        return( m_date.format_string( false ) + STR_SEPARATOR + m_name );
    }

    @Override
    public String getListStrSecondary() {
        return m_date.is_ordinal() ? Date.format_string_do( m_date_changed )
                                  : m_date.getWeekdayStr();
    }

    @Override
    public int get_icon() {
        return( m_option_favored ? R.drawable.ic_favorite : R.drawable.ic_entry );
    }

    public String get_text() {
        return m_text;
    }

    public void set_text( String text ) {
        m_text = text;
    }

    // FILTERING
    boolean get_filtered_out() {
        // TODO: recalculate here when necessary
        return m_flag_filtered_out;
    }

    void set_filtered_out( boolean filteredout ) {
        m_flag_filtered_out = filteredout;
    }

    // LANGUAGE
    public String get_lang() {
        return m_option_lang;
    }

    public String get_lang_final() {
        return m_option_lang.compareTo( Lifeobase.LANG_INHERIT_DIARY ) == 0 ? m_diary.get_lang()
                                                                           : m_option_lang;
    }

    void set_lang( String lang ) {
        m_option_lang = lang;
    }

    // TRASH FUNCTIONALITY
    boolean is_trashed() {
        return m_option_trashed;
    }

    void set_trashed( boolean trashed ) {
        m_option_trashed = trashed;
    }

    // TAGS
    public boolean add_tag( Tag tag ) {
        if( m_tags.add( tag ) ) {
            tag.add_entry( this );
            return true;
        }
        else
            return false;
    }

    public boolean remove_tag( Tag tag ) {
        if( m_tags.remove( tag ) ) {
            tag.remove_entry( this );
            return true;
        }
        else
            return false;
    }

    protected void calculate_title( String text ) {
        if( text.length() < 1 ) {
            m_name = Lifeobase.getStr( R.string.empty_entry );
        }
        else {
            int pos = text.indexOf( '\n' );
            if( pos == -1 )
                m_name = text;
            else
                m_name = text.substring( 0, pos );
        }
    }

    public Theme get_theme() {
        return m_ptr2theme;
    }

    public boolean get_theme_is_set() {
        return( m_ptr2theme != null );
    }

    public void set_theme( Theme theme ) {
        m_ptr2theme = theme;
    }

    public Date m_date;
    public long m_date_created;
    public long m_date_changed;
    public String m_text = new String();
    protected java.util.List< Tag > m_tags = new ArrayList< Tag >();
    protected Theme m_ptr2theme;

    protected boolean m_option_favored = false;
    protected String m_option_lang = Lifeobase.LANG_INHERIT_DIARY; // empty means off
    protected boolean m_option_trashed = false;
    protected boolean m_flag_filtered_out = false;
}
