package com.facebook;

import android.os.AsyncTask;
import android.os.Handler;
import android.util.Log;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes.dex */
public class GraphRequestAsyncTask extends AsyncTask<Void, Void, List<GraphResponse>> {
    private static final String TAG = GraphRequestAsyncTask.class.getCanonicalName();
    private static Method executeOnExecutorMethod;
    private final HttpURLConnection connection;
    private Exception exception;
    private final GraphRequestBatch requests;

    static {
        Method[] arr$ = AsyncTask.class.getMethods();
        for (Method method : arr$) {
            if ("executeOnExecutor".equals(method.getName())) {
                Class<?>[] parameters = method.getParameterTypes();
                if (parameters.length == 2 && parameters[0] == Executor.class && parameters[1].isArray()) {
                    executeOnExecutorMethod = method;
                    return;
                }
            }
        }
    }

    public GraphRequestAsyncTask(GraphRequest... requests) {
        this((HttpURLConnection) null, new GraphRequestBatch(requests));
    }

    public GraphRequestAsyncTask(Collection<GraphRequest> requests) {
        this((HttpURLConnection) null, new GraphRequestBatch(requests));
    }

    public GraphRequestAsyncTask(GraphRequestBatch requests) {
        this((HttpURLConnection) null, requests);
    }

    public GraphRequestAsyncTask(HttpURLConnection connection, GraphRequest... requests) {
        this(connection, new GraphRequestBatch(requests));
    }

    public GraphRequestAsyncTask(HttpURLConnection connection, Collection<GraphRequest> requests) {
        this(connection, new GraphRequestBatch(requests));
    }

    public GraphRequestAsyncTask(HttpURLConnection connection, GraphRequestBatch requests) {
        this.requests = requests;
        this.connection = connection;
    }

    protected final Exception getException() {
        return this.exception;
    }

    protected final GraphRequestBatch getRequests() {
        return this.requests;
    }

    public String toString() {
        return "{RequestAsyncTask:  connection: " + this.connection + ", requests: " + this.requests + "}";
    }

    @Override // android.os.AsyncTask
    protected void onPreExecute() {
        super.onPreExecute();
        if (this.requests.getCallbackHandler() == null) {
            this.requests.setCallbackHandler(new Handler());
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public void onPostExecute(List<GraphResponse> result) {
        super.onPostExecute(result);
        if (this.exception != null) {
            Log.d(TAG, String.format("onPostExecute: exception encountered during request: %s", this.exception.getMessage()));
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public List<GraphResponse> doInBackground(Void... params) {
        List<GraphResponse> listExecuteConnectionAndWait;
        try {
            if (this.connection == null) {
                listExecuteConnectionAndWait = this.requests.executeAndWait();
            } else {
                listExecuteConnectionAndWait = GraphRequest.executeConnectionAndWait(this.connection, this.requests);
            }
            return listExecuteConnectionAndWait;
        } catch (Exception e) {
            this.exception = e;
            return null;
        }
    }

    GraphRequestAsyncTask executeOnSettingsExecutor() {
        if (executeOnExecutorMethod != null) {
            try {
                executeOnExecutorMethod.invoke(this, FacebookSdk.getExecutor(), null);
            } catch (IllegalAccessException e) {
            } catch (InvocationTargetException e2) {
            }
        } else {
            execute(new Void[0]);
        }
        return this;
    }
}
