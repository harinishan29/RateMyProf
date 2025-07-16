package com.saveetha.ratemyprof

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.saveetha.ratemyprof.models.SelectionData
import com.saveetha.ratemyprof.ui.theme.RateMyProfTheme

@Composable
fun SelectUniversityDepartmentScreen(
    onSubmit:(SelectionData) -> Unit
) {
    val universities = listOf("SSE", "SEC", "SCLAS")

    val departmentsMap = mapOf(
        "SSE" to listOf("CSE", "ECE", "EEE"),
        "SEC" to listOf("CSE", "Mechanical", "AI & DS"),
        "SCLAS" to listOf("BCA", "BCOM", "MCA")
    )

    var selectedUniversity by remember { mutableStateOf("") }
    var selectedDepartment by remember { mutableStateOf("") }
    var showMessage by remember { mutableStateOf(false) }

    val departments = departmentsMap[selectedUniversity] ?: emptyList()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        Text("Select University and Department", style = MaterialTheme.typography.headlineSmall)

        // University Dropdown
        DropdownSelector(
            label = "Select University",
            options = universities,
            selectedOption = selectedUniversity,
            onOptionSelected = {
                selectedUniversity = it
                selectedDepartment = "" // Reset department on university change
            }
        )

        // Department Dropdown
        if (selectedUniversity.isNotEmpty()) {
            DropdownSelector(
                label = "Select Department",
                options = departments,
                selectedOption = selectedDepartment,
                onOptionSelected = { selectedDepartment = it }
            )
        }

        // Submit Button
        Button(
            onClick = {
                showMessage = true
                onSubmit(SelectionData(selectedUniversity, selectedDepartment))
            },
            enabled = selectedUniversity.isNotEmpty() && selectedDepartment.isNotEmpty(),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Submit")
        }

        if (showMessage) {
            Text(
                text = "You selected $selectedDepartment from $selectedUniversity",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(top = 16.dp)
            )
        }
    }
}

@Composable
fun DropdownSelector(
    label: String,
    options: List<String>,
    selectedOption: String,
    onOptionSelected: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Column {
        Text(label, style = MaterialTheme.typography.bodyMedium)
        OutlinedTextField(
            value = selectedOption,
            onValueChange = {},
            readOnly = true,
            modifier = Modifier
                .fillMaxWidth()
                .clickable { expanded = true },
            trailingIcon = {
                IconButton(onClick = { expanded = true }) {
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "Dropdown Icon")
                }
            }
        )
        DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            options.forEach { option ->
                DropdownMenuItem(
                    text = { Text(option) },
                    onClick = {
                        onOptionSelected(option)
                        expanded = false
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SelectUniversityDepartmentScreenPreview() {
    RateMyProfTheme {
        SelectUniversityDepartmentScreen(onSubmit = {})
    }
}
