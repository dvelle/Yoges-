package precisioninfomatics.servicefirst.CursorLoaders;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.support.v4.content.CursorLoader;

import precisioninfomatics.servicefirst.DAO.VisitDAO;
import precisioninfomatics.servicefirst.DAO.VisitStatusDAO;

/**
 * Created by 4264 on 23/08/2017.
 */

public class VisitLoaders  extends CursorLoader {
    private VisitDAO visitDAO;
    private Integer id;
    private ForceLoadContentObserver mObserver = new  ForceLoadContentObserver();
    public VisitLoaders(Context context, Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder, int  id) {
        super(context, uri, projection, selection, selectionArgs, sortOrder);
        visitDAO=new VisitDAO(context);
        this.id=id;

    }



    @Override
    public Cursor loadInBackground() {
        Cursor c = visitDAO.visitList(id);
        if (c != null) {
            c.getCount();
            c.registerContentObserver(mObserver);
            c.setNotificationUri(getContext().getContentResolver(), getUri());
        }
        return c;
    }

}
