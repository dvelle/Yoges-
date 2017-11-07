package precisioninfomatics.servicefirst.CursorLoaders;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.support.v4.content.CursorLoader;

import precisioninfomatics.servicefirst.DAO.LocalVendorDAO;

/**
 * Created by 4264 on 23/08/2017.
 */

public class LocalVendorLoaders extends CursorLoader {
    private final ForceLoadContentObserver mObserver = new ForceLoadContentObserver();
    private LocalVendorDAO localVendorDAO;
    private Integer IncidentID;
    public LocalVendorLoaders(Context context, Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder,int incidentID) {
        super(context, uri, projection, selection, selectionArgs, sortOrder);
        this.localVendorDAO=new LocalVendorDAO(context);
        this.IncidentID=incidentID;
    }

    @Override
    public Cursor loadInBackground() {
        Cursor c;
        c = localVendorDAO.LocalVendorList(IncidentID);
        if (c != null) {
            c.getCount();
            c.registerContentObserver(mObserver);
            c.setNotificationUri(getContext().getContentResolver(), getUri());
        }
        return c;
    }
}
