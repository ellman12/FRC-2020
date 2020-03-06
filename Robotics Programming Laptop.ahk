#NoEnv  ; Recommended for performance and compatibility with future AutoHotkey releases.
; #Warn  ; Enable warnings to assist with detecting common errors.
SendMode Input  ; Recommended for new scripts due to its superior speed and reliability.
SetWorkingDir %A_ScriptDir%  ; Ensures a consistent starting directory.
#SingleInstance force

SetTitleMatchMode, 2

;Variables for F6 group stuff
;Tracks all windows you want as part of your custom group
Global WindowGroupF6 := []
;Tracks the current window you're on
Global CurrentWinF6 := 1

;Variables for F7 group stuff
;Tracks all windows you want as part of your custom group
Global WindowGroupF7 := []
;Tracks the current window you're on
Global CurrentWinF7 := 1

#IfWinActive Notepad
^w::
Send, !{F4}
return

#IfWinActive Visual Studio Code
SC029::
Send, {SC029}
return

^SC029::
Send, !{Tab}
return

:*:sys::
Send, System.out.println("");
Sleep 20
Send, {Left 3}
return

#IfWinActive SciTE4AutoHotkey
^SC029::
Send, {F5}
return

;When Cortana/Search is open, RWin does LWin twice.
#IfWinActive Cortana
RWin::
Send, {LWin}
Sleep 300
Send, {LWin}
return

;Sends a Space and "meaning" when the Windows 10 Search Bar window is active (Cortana).
;Equivalent to G15 in Browser.
!s::
Send, {Space}meaning
return
#If

;Close Shuffleboard with Shift + F4.
+F4::
WinClose, Shuffleboard
return

;Sends the current date and time in this format: 10/31/2019 07:43 PM.
:*:datetime::
FormatTime, CurrentDateTime,, M/dd/yyyy h:mm tt
SendInput, %CurrentDateTime%
return

;Same thing, but just the date.
:*:currdate::
FormatTime, CurrentDateTime,, M/dd/yyyy
SendInput, %CurrentDateTime%
return

;Same thing, but just the time.
:*:currtime::
FormatTime, CurrentDateTime,, h:mm tt
SendInput, %CurrentDateTime%
return

;Sends "Created on currdate."
#c::
FormatTime, CurrentDateTime,, M/dd/yyyy
SendInput, Created on %CurrentDateTime%.
return

;Open FRC-2020 GitHub folder.
^+f::
Run, %A_MyDocuments%\GitHub\FRC-2020
return

;Opens the FRC-2020 GitHub repo in Chrome.
^+g::
Run, "https://github.com/ellman12/FRC-2020"
return

;Send the color sensor library link.
^+c::
Send, http://revrobotics.com/content/sw/color-sensor-v3/sdk/REVColorSensorV3.json
return

;Send the link to the Spark Max library.
^+s::
Send, https://www.revrobotics.com/content/sw/max/sdk/REVRobotics.json
return

;Send the entire Robot.java template.txt file. VSCode doesn't format it at all, so in VSCode you have to do Alt + Shift + f. Works great, though.
^+r::

SendRaw,package frc.robot`;`n`nimport edu.wpi.first.wpilibj.TimedRobot`;`n`npublic class Robot extends TimedRobot {`n`n  @Override`n  public void robotInit() {`n`n  }`n`n  @Override`n  public void robotPeriodic() {`n  }`n`n  @Override`n  public void autonomousInit() {`n`n  }`n`n  @Override`n  public void autonomousPeriodic() {`n`n  }`n`n  @Override`n  public void teleopPeriodic() {`n`n  }`n`n  @Override`n  public void testPeriodic() {`n  }`n}

return

;Send my name.
^+e::
Send, Elliott DuCharme.
return

;Send my name and Larry's.
^!+e::
Send, Elliott DuCharme and Larry Basegio.
return

;Send my name, Larry's name, and Noah's name.
^!+#e::
Send, Elliott DuCharme, Larry Basegio and Noah Stigeler.
return

^Space::
WinSet, AlwaysOnTop, Toggle, A
return

CapsLock::
return

;Suspends hotkeys.
#p::
Suspend
return

;Just in case lol.
!p::
Reload
return

:*:ps4::PS4
:*:PS$::PS4

;Click on name of program folder in vs-workspace, and hit Windows key + o.
#o::
Send, {Enter}
Sleep 200
Send, {Down 7}
Sleep 200
Send, {Enter}
Sleep 200
Send, {Down}
Sleep 200
Send, {Enter}
Sleep 200
Send, {Down}
Sleep 200
Send, {Enter}
Sleep 200
Send, {Down}
Sleep 200
Send, {Enter}
Sleep 200
Send, {Down}
Sleep 75
Send, {Enter}
Sleep 75
return

;Minimize active window.
!-::
WinMinimize, A
return

;Maximize active window.
!=::
WinMaximize, A
return

;Open battery menu thing.
#b::
MouseGetPos, winBMouseX, winBMouseY
MouseMove, 1670, 1055, 0
Sleep 200
Send, {Click}
return

;Open Wi-Fi menu thing.
#w::
MouseGetPos, winWMouseX, winWMouseY
MouseMove, 1732, 1055, 0
Sleep 200
Send, {Click}
return

;**************************APPLICATION SWITCHER STUFF***************************************
F1::
switchToVSAndTabs()
{
IfWinNotExist, ahk_class Chrome_WidgetWin_1
	Run, C:\Users\Public\wpilib\2020\vscode\Code.exe
if WinActive("ahk_exe Code.exe")
	Send ^{PGDN}
else
	WinActivate ahk_exe Code.exe
}

F2::
switchToOtherVSWindows()
{
Process, Exist, Code.exe
	If errorLevel = 0
		Run, C:\Users\Public\wpilib\2020\vscode\Code.exe
	else
	{
	GroupAdd, taranCodes, ahk_exe Code.exe
	if WinActive("ahk_exe Code.exe")
		GroupActivate, taranCodes, r
	else
		WinActivate ahk_exe Code.exe
	}
}

F3::
switchToChromeAndTabs()
{
IfWinNotExist, ahk_class Chrome_WidgetWin_1
	Run, chrome.exe
if WinActive("ahk_exe chrome.exe")
	Send ^{PGDN}
else
	WinActivate ahk_exe chrome.exe
}

F4::
switchToOtherChromeWindows()
{
Process, Exist, chrome.exe
	If errorLevel = 0
		Run, chrome.exe
	else
	{
	GroupAdd, taranchromes, ahk_class Chrome_WidgetWin_1
	if WinActive("ahk_class ahk_class Chrome_WidgetWin_1")
		GroupActivate, taranchromes, r
	else
		WinActivate ahk_class Chrome_WidgetWin_1
	}
}

;~ ;MR button on my keyboard
;~ ^!F1::
;~ switchToExplorer(){
;~ IfWinNotExist, ahk_class CabinetWClass
	;~ Run, explorer.exe
;~ GroupAdd, taranexplorers, ahk_class CabinetWClass
;~ if WinActive("ahk_exe explorer.exe")
	;~ GroupActivate, taranexplorers, r
;~ else
	;~ WinActivate ahk_class CabinetWClass ;you have to use WinActivatebottom if you didn't create a window group.
;~ }

F9::
if WinActive("ahk_exe Code.exe") {
	Send ^{PGUP}
} else if WinActive("ahk_class Chrome_WidgetWin_1") {
	Send ^+{tab}
}
;~ if WinActive("ahk_exe explorer.exe")
	;~ Send !{left} ;alt left is the explorer shortcut to go "back" or "down" one folder level.
return



;****************************************************CUSTOM GROUP STUFF****************************************************
;*************************************************F6 GROUP STUFF*************************************************
;Credits for this F6 stuff goes to GroggyOtter from the AHK subreddit:
;https://www.reddit.com/r/AutoHotkey/comments/dbil7u/add_active_window_to_group_and_push_button_to/f241ak3?utm_source=share&utm_medium=web2x
;I don't really know how it works, but it's fantastic. Thanks m8.
;I modified it a teeny tiny bit, plus added the F7 one. It's really nice having this for both F6 and F7.
^F6::
AddWindowF6()
return
^!F6::
RemoveWindowF6()
return
F6::
NextWindowF6()
return
+F6::
PrevWindowF6()
return

AddWindowF6(){
	ToolTip, Added to F6 Group!
	Sleep, 200
	ToolTip
	
	; Get the active window's ID
	WinGet, thisIDF6, ID, A
	; Value to track if ID was found in the group
	foundF6 := False
	; Loop through all the IDs
	for indexF6, value in WindowGroupF6
	{
		; If the current ID was found inside the array
		If (valueF6 = thisIDF6){
			; Then mark it as found and break the loop
			foundF6 = True
			Break
		}
	}
	
	; If the ID was never found in the array
	If (foundF6 = False)
		; Add it to the array
		WindowGroupF6.Push(thisIDF6)
	Return
}

RemoveWindowF6(){
	ToolTip, Removed from F6 Group!
	Sleep, 200
	ToolTip
	
	WinGet, thisIDF6, ID, A
	foundF6 := False
	for indexF6, value in WindowGroupF6
	{
		; Same as the AddWindow function except if a match is found, remove it from the array
		If (valueF6 = thisIDF6){
			WindowGroupF6.RemoveAt(indexF6)
			Break
		}
	}
	Return
}

NextWindowF6(){
	; Increment the current window by 1
	CurrentWinF6++
	; If the current window is greater than the number of entries in the array
	If (CurrentWinF6 > WindowGroupF6.MaxIndex())
		; Then reset it to the lowest index
		CurrentWinF6 := WindowGroupF6.MinIndex()
	; Now activate the window based on CurrentWin
	WinActivate, % "ahk_id " WindowGroupF6[CurrentWinF6]
	Return
}

PrevWindowF6(){
	; Decrement the current window by 1
	CurrentWinF6--
	; If it's lower than the lowest index, set CurrentWindow to the maxindex
	If (CurrentWinF6 < WindowGroupF6.MinIndex())
		CurrentWinF6 := WindowGroupF6.MaxIndex()
	; Now activate that window based on CurrentWin
	WinActivate, % "ahk_id " WindowGroupF6[CurrentWinF6]
	Return
}

;*************************************************F7 GROUP STUFF*************************************************
^F7::
AddWindowF7()
return
^!F7::
RemoveWindowF7()
return
F7::
NextWindowF7()
return
+F7::
PrevWindowF7()
return

AddWindowF7(){
	ToolTip, Added to F7 Group!
	Sleep, 200
	ToolTip
	
	; Get the active window's ID
	WinGet, thisIDF7, ID, A
	; Value to track if ID was found in the group
	foundF7 := False
	; Loop through all the IDs
	for indexF7, value in WindowGroupF7
	{
		; If the current ID was found inside the array
		If (valueF7 = thisIDF7){
			; Then mark it as found and break the loop
			foundF7 = True
			Break
		}
	}
	
	; If the ID was never found in the array
	If (foundF7 = False)
		; Add it to the array
		WindowGroupF7.Push(thisIDF7)
	Return
}

RemoveWindowF7(){
	ToolTip, Removed from F7 Group!
	Sleep, 200
	ToolTip
	
	WinGet, thisIDF7, ID, A
	foundF7 := False
	for indexF7, value in WindowGroupF7
	{
		; Same as the AddWindow function except if a match is found, remove it from the array
		If (valueF7 = thisIDF7){
			WindowGroupF7.RemoveAt(indexF7)
			Break
		}
	}
	Return
}

NextWindowF7(){
	; Increment the current window by 1
	CurrentWinF7++
	; If the current window is greater than the number of entries in the array
	If (CurrentWinF7 > WindowGroupF7.MaxIndex())
		; Then reset it to the lowest index
		CurrentWinF7 := WindowGroupF7.MinIndex()
	; Now activate the window based on CurrentWin
	WinActivate, % "ahk_id " WindowGroupF7[CurrentWinF7]
	Return
}

PrevWindowF7(){
	; Decrement the current window by 1
	CurrentWinF7--
	; If it's lower than the lowest index, set CurrentWindow to the maxindex
	If (CurrentWinF7 < WindowGroupF7.MinIndex())
		CurrentWinF7 := WindowGroupF7.MaxIndex()
	; Now activate that window based on CurrentWin
	WinActivate, % "ahk_id " WindowGroupF7[CurrentWinF7]
	Return
}

;Send the file copy-and-paste.
^#f::
Send, /////////////////////////////////////////////////////////////////////{Enter}// File: {Enter}/////////////////////////////////////////////////////////////////////{Enter}//{Enter}// Purpose: {Enter}//{Enter}// Authors: {Enter}//{Enter}// Environment: Microsoft VSCode Java.{Enter}//{Enter}// Remarks: {Enter}//{Enter}/////////////////////////////////////////////////////////////////////{Enter}/////////////////////////////////////////////////////////////////////{Enter}
return

;Send the method (function) copy-and-paste.
^#m::
Send, /////////////////////////////////////////////////////////////////////{Enter}// Function: {Enter}/////////////////////////////////////////////////////////////////////{Enter}//{Enter}// Purpose: {Enter}//{Enter}// Arguments: {Enter}//{Enter}// Returns: {Enter}//{Enter}// Remarks: {Enter}//{Enter}/////////////////////////////////////////////////////////////////////{Enter}/////////////////////////////////////////////////////////////////////{Enter}
return

/*
;TEMP STUFF
;Copy and paste src files into to a new folder to upload to GitHub.
!b::
;Get name
Send, !h
Sleep 1000
Send, r
Sleep 1000
Send, ^c
Sleep 1000
Send, {Escape}
Send, 900
;~ FolderText = ClipboardAll

;Create the folder
Send, !{Tab}
Sleep 1000
Send, ^+n
Sleep 3000
Send, ^v
Sleep 1000
Send, {Enter 2}
Sleep 1000
Send, !{Tab}
SLeep 1500

;Dig through folders
Send, {Enter}
Sleep 200
Send, {Down 7}
Sleep 200
Send, {Enter}
Sleep 200
Send, {Down}
Sleep 200
Send, {Enter}
Sleep 200
Send, {Down}
Sleep 200
Send, {Enter}
Sleep 200
Send, {Down}
Sleep 200
Send, {Enter}
Sleep 200
Send, {Down}
Sleep 75
Send, {Enter}
Sleep 1000

;Copy and paste
Send, ^a
Sleep 1000
Send, ^c
Sleep 1000
Send, !{Tab}
Sleep 1000
Send, ^v
Sleep 2000
Send, !{Up}
Sleep 1000
Send, !{Tab}
Sleep 3000
Send, !{Up 6}
Sleep 1300

Send, {Down}
return

;Do the same thing, but in case there's a 1.0.0 folder.
^!b::
;Get name
Send, !h
Sleep 1000
Send, r
Sleep 1000
Send, ^c
Sleep 1000
Send, {Escape}
Send, 900
;~ FolderText = ClipboardAll

;Create the folder
Send, !{Tab}
Sleep 1000
Send, ^+n
Sleep 3000
Send, ^v
Sleep 1000
Send, {Enter 2}
Sleep 1000
Send, !{Tab}
SLeep 1500

;Dig through folders
Send, {Enter}
Sleep 200
Send, {Down}
Sleep 200
Send, {Enter}
Sleep 200
Send, {Down 7}
Sleep 200
Send, {Enter}
Sleep 200
Send, {Down}
Sleep 200
Send, {Enter}
Sleep 200
Send, {Down}
Sleep 200
Send, {Enter}
Sleep 200
Send, {Down}
Sleep 200
Send, {Enter}
Sleep 200
Send, {Down}
Sleep 75
Send, {Enter}
Sleep 1000

;Copy and paste
Send, ^a
Sleep 1000
Send, ^c
Sleep 1000
Send, !{Tab}
Sleep 1000
Send, ^v
Sleep 2000
Send, !{Up}
Sleep 1000
Send, !{Tab}
Sleep 3000
Send, !{Up 7}
Sleep 1300

Send, {Down}
return
*/