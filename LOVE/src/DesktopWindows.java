import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.BaseTSD;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.platform.win32.WinDef.HWND;
import com.sun.jna.platform.win32.WinNT;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.win32.W32APIOptions;

import java.io.IOException;

import static com.sun.jna.platform.win32.WinDef.*;

public class DesktopWindows {

    public static final int  LVM_GETITEMCOUNT = 0x1000 + 4;
    public static final int  LVM_SETITEMPOSITION = 0x1000 + 15;
    public static final int LVM_GETITEMPOSITION = 0x1000 + 16;

    public static HWND get_HWND_GETDESKTOP() {
        HWND hWnd_Progman = User32.INSTANCE.FindWindow("Progman", "Program Manager");
        HWND hWnd_SHELLDLL_DefView = User32.INSTANCE.FindWindowEx(hWnd_Progman, null, "SHELLDLL_DefView", null);
        HWND hWnd_SysListView32 = User32.INSTANCE.FindWindowEx(hWnd_SHELLDLL_DefView, null, "SysListView32", "FolderView");
        return hWnd_SysListView32;
    }

    public static int getDesktopIconsCount() {
        HWND HWND_GETDESKTOP = get_HWND_GETDESKTOP();
        int iconsOfDesktop = User32.INSTANCE.SendMessage(HWND_GETDESKTOP,LVM_GETITEMCOUNT, null, null).intValue();
        return iconsOfDesktop;
    }

    public static void goToXY(int x, int y , int ord) {
        LPARAM lparam = new LPARAM((y << 0x10) | (x & 0xffff));
        WPARAM wparam = new WPARAM(ord);
        User32.INSTANCE.SendMessage(get_HWND_GETDESKTOP(),LVM_SETITEMPOSITION, wparam, lparam);
    }


    private interface Kernel32 extends com.sun.jna.platform.win32.Kernel32 {

        Kernel32 INSTANCE = Native.load("kernel32", Kernel32.class, W32APIOptions.DEFAULT_OPTIONS);

        Pointer VirtualAllocEx(HANDLE hProcess, Pointer lpAddress, SIZE_T dwSize, int flAllocationType, int flProtect);

        boolean VirtualFreeEx(HANDLE hProcess, Pointer lpAddress, SIZE_T dwSize, int dwFreeType);

        int MEM_COMMIT = 0x00001000;
    }

    public static POINT getItemPosition(int indexIcon) throws IOException, InterruptedException {

        // Get the SysListView32 process handle.
        IntByReference processIdRef = new IntByReference();
        User32.INSTANCE.GetWindowThreadProcessId(DesktopWindows.get_HWND_GETDESKTOP(), processIdRef);
        WinNT.HANDLE procHandle = Kernel32.INSTANCE.OpenProcess(
                Kernel32.PROCESS_VM_OPERATION | Kernel32.PROCESS_VM_WRITE | Kernel32.PROCESS_VM_READ,
                false, processIdRef.getValue());

        // Allocate memory in the SysView32 process.
        int pointSize = new WinDef.POINT().size(); // 8 bytes.
        Pointer pMem = Kernel32.INSTANCE.VirtualAllocEx(procHandle, null, new BaseTSD.SIZE_T(pointSize),
                Kernel32.MEM_COMMIT, Kernel32.PAGE_READWRITE);

            // Send the LVM_GETITEMPOSITION message to the SysListView32.
            WinDef.LRESULT res = User32.INSTANCE.SendMessage(
                    DesktopWindows.get_HWND_GETDESKTOP(), LVM_GETITEMPOSITION, new WinDef.WPARAM(indexIcon), new WinDef.LPARAM(Pointer.nativeValue(pMem)));

            if(res.intValue() != 1) {
                throw new IllegalStateException("Message sending failed");
            }

            // Read the earlier POINT-sized written memory.
            WinDef.POINT point = new WinDef.POINT();
            IntByReference read = new IntByReference();
            boolean success = Kernel32.INSTANCE.ReadProcessMemory(
                    procHandle, pMem, point.getPointer(), pointSize, read);

            if (!success) {
                System.out.println("Read error = " + Kernel32.INSTANCE.GetLastError());
                System.exit(1);
            }
            point.read();
            System.out.println("Point found: x=" + point.x + ", y=" + point.y);
            return  point;
        }
    }






