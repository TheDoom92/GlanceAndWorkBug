package com.example.myapplication

import androidx.compose.runtime.Composable
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.text.Text

class WorkerWidget : GlanceAppWidget() {

    @Composable
    override fun Content() {
        Text("LOADED")
    }
}