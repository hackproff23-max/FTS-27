package defpackage;

import com.facebook.internal.ServerProtocol;
import java.util.Vector;

/* JADX INFO: loaded from: classes.dex */
public class dl extends cy implements df {
    private static final bm c = d.a(dl.class);
    public String b;
    private dm d;
    private Object e;
    private cd f;

    public dl(String str, short s, String str2, byte[] bArr, String str3, bi biVar, bi biVar2, String str4, Vector vector, dh dhVar) {
        Vector vector2;
        long j;
        String str5;
        int i;
        boolean z;
        super(str, s, str3, biVar, biVar2);
        if (str2 == null) {
            throw new IllegalArgumentException(" application id is null.");
        }
        this.b = str2;
        if (bArr == null) {
            throw new IllegalArgumentException(" application key is null");
        }
        boolean z2 = true;
        long j2 = 5242880;
        int i2 = 7;
        String str6 = null;
        if (vector == null || vector.size() == 0) {
            vector2 = new Vector();
            j = 5242880;
            str5 = null;
            i = 7;
            z = true;
        } else {
            Vector vector3 = new Vector(vector.size());
            int i3 = 0;
            while (true) {
                int i4 = i3;
                if (i4 >= vector.size()) {
                    break;
                }
                cz czVar = (cz) vector.elementAt(i4);
                if (czVar.d() == cz.a.a) {
                    if (czVar.a().equals("DEVICE_CMD_LOG_TO_SERVER_ENABLED")) {
                        this.e = new Vector();
                    } else if (czVar.a().equals("Calllog_Disable")) {
                        if (new String(czVar.b()).equals("TRUE") || new String(czVar.b()).equals(ServerProtocol.DIALOG_RETURN_SCOPES_TRUE)) {
                            z2 = false;
                        } else if (new String(czVar.b()).equals("FALSE") || new String(czVar.b()).equals("false")) {
                            z2 = true;
                        }
                    } else if (czVar.a().equals("Calllog_Root_Id")) {
                        str6 = new String(czVar.b());
                    } else if (czVar.a().equals("Calllog_Size_Limit")) {
                        j2 = Long.parseLong(new String(czVar.b()));
                    } else if (czVar.a().equals("Calllog_Retention_Days")) {
                        i2 = Integer.parseInt(new String(czVar.b()));
                    }
                }
                vector3.addElement(czVar);
                i3 = i4 + 1;
            }
            j = j2;
            str5 = str6;
            i = i2;
            z = z2;
            vector2 = vector3;
        }
        if (str4 == null && z) {
            c.e("NullPointerException calllogFileName is NULL.");
            throw new NullPointerException("calllogFileName can not be null!");
        }
        if (j < 0) {
            c.e("NMSP_DEFINES_CALLLOG_FILE_SIZE_LIMIT can not be negative!");
            throw new NullPointerException("NMSP_DEFINES_CALLLOG_FILE_SIZE_LIMIT can not be negative!");
        }
        this.d = new dm(b(), c(), str2, bArr, str3, vector2, c_(), dhVar);
        if (z) {
            this.f = new ch(str4, j, i, str5, this.a, this.b, c_(), this.d.b());
            this.d.a(((ch) this.f).b());
        }
    }

    @Override // defpackage.df
    public final cd a() {
        return this.f;
    }

    @Override // defpackage.df
    public final void b_() {
        ce ceVarB;
        c.b("shutdown");
        if (this.f != null && (ceVarB = ((ch) this.f).b()) != null) {
            ceVarB.a("NMSP_ShutDown").a();
        }
        this.d.e();
    }

    public final dm h() {
        return this.d;
    }

    public final Object i() {
        return this.e;
    }
}
