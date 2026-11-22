package com.google.android.gms.internal;

import com.google.android.gms.internal.qq;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class qr<M extends qq<M>, T> {
    protected final Class<T> ayX;
    protected final boolean ayY;
    public final int tag;
    protected final int type;

    private qr(int i, Class<T> cls, int i2, boolean z) {
        this.type = i;
        this.ayX = cls;
        this.tag = i2;
        this.ayY = z;
    }

    public static <M extends qq<M>, T extends qw> qr<M, T> a(int i, Class<T> cls, int i2) {
        return new qr<>(i, cls, i2, false);
    }

    private T n(List<qy> list) {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            qy qyVar = list.get(i);
            if (qyVar.azi.length != 0) {
                a(qyVar, arrayList);
            }
        }
        int size = arrayList.size();
        if (size == 0) {
            return null;
        }
        T tCast = this.ayX.cast(Array.newInstance(this.ayX.getComponentType(), size));
        for (int i2 = 0; i2 < size; i2++) {
            Array.set(tCast, i2, arrayList.get(i2));
        }
        return tCast;
    }

    private T o(List<qy> list) {
        if (list.isEmpty()) {
            return null;
        }
        return this.ayX.cast(x(qo.p(list.get(list.size() - 1).azi)));
    }

    int B(Object obj) {
        return this.ayY ? C(obj) : D(obj);
    }

    protected int C(Object obj) {
        int iD = 0;
        int length = Array.getLength(obj);
        for (int i = 0; i < length; i++) {
            if (Array.get(obj, i) != null) {
                iD += D(Array.get(obj, i));
            }
        }
        return iD;
    }

    protected int D(Object obj) {
        int iHl = qz.hl(this.tag);
        switch (this.type) {
            case 10:
                return qp.b(iHl, (qw) obj);
            case 11:
                return qp.c(iHl, (qw) obj);
            default:
                throw new IllegalArgumentException("Unknown type " + this.type);
        }
    }

    protected void a(qy qyVar, List<Object> list) {
        list.add(x(qo.p(qyVar.azi)));
    }

    void a(Object obj, qp qpVar) throws IOException {
        if (this.ayY) {
            c(obj, qpVar);
        } else {
            b(obj, qpVar);
        }
    }

    protected void b(Object obj, qp qpVar) {
        try {
            qpVar.hd(this.tag);
            switch (this.type) {
                case 10:
                    int iHl = qz.hl(this.tag);
                    qpVar.b((qw) obj);
                    qpVar.x(iHl, 4);
                    return;
                case 11:
                    qpVar.c((qw) obj);
                    return;
                default:
                    throw new IllegalArgumentException("Unknown type " + this.type);
            }
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    protected void c(Object obj, qp qpVar) {
        int length = Array.getLength(obj);
        for (int i = 0; i < length; i++) {
            Object obj2 = Array.get(obj, i);
            if (obj2 != null) {
                b(obj2, qpVar);
            }
        }
    }

    final T m(List<qy> list) {
        if (list == null) {
            return null;
        }
        return this.ayY ? n(list) : o(list);
    }

    protected Object x(qo qoVar) {
        Class componentType = this.ayY ? this.ayX.getComponentType() : this.ayX;
        try {
            switch (this.type) {
                case 10:
                    qw qwVar = (qw) componentType.newInstance();
                    qoVar.a(qwVar, qz.hl(this.tag));
                    return qwVar;
                case 11:
                    qw qwVar2 = (qw) componentType.newInstance();
                    qoVar.a(qwVar2);
                    return qwVar2;
                default:
                    throw new IllegalArgumentException("Unknown type " + this.type);
            }
        } catch (IOException e) {
            throw new IllegalArgumentException("Error reading extension field", e);
        } catch (IllegalAccessException e2) {
            throw new IllegalArgumentException("Error creating instance of class " + componentType, e2);
        } catch (InstantiationException e3) {
            throw new IllegalArgumentException("Error creating instance of class " + componentType, e3);
        }
    }
}
