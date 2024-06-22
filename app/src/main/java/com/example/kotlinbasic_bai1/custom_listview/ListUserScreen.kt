package com.example.kotlinbasic_bai1.custom_listview

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.kotlinbasic_bai1.ui.theme.KotlinBasic_Bai1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KotlinBasic_Bai1Theme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    MyApp()
                }
            }
        }
    }
}

@Composable
fun MyApp() {
    Column(modifier = Modifier.fillMaxSize()) {
        var userList by remember { mutableStateOf(listOf<User>()) }

        AddUserButton(onAddUser = { newUser ->
            userList = userList + newUser
        })

        UserList(userList = userList) { newUser ->
            userList = userList + newUser
        }

    }
}

@Composable
fun UserList(
    userList: List<User>,
    onAddUser: (User) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        items(userList) { user ->
            UserItem(user = user)
        }
    }
}

@Composable
fun UserItem(user: User) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = "Name: ${user.name}")
            Text(text = "Age: ${user.age}")
            Text(text = "Gender: ${user.gender}")
        }
    }
}

@Composable
fun AddUserButton(onAddUser: (User) -> Unit) {
    val showDialog = remember { mutableStateOf(false) }

    Button(
        onClick = { showDialog.value = true },
        modifier = Modifier
            .padding(16.dp)
    ) {
        Text(text = "Add User")
    }

    if (showDialog.value) {
        AddUserDialog(
            onDismiss = { showDialog.value = false },
            onAddUser = onAddUser
        )
    }
}

@Composable
fun AddUserDialog(
    onDismiss: () -> Unit,
    onAddUser: (User) -> Unit
) {
    var name by remember { mutableStateOf("") }
    var age by remember { mutableStateOf("") }
    var gender by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Add User") },
        text = {
            Column {
                TextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Name") }
                )

                TextField(
                    value = age,
                    onValueChange = { age = it },
                    label = { Text("Age") }
                )

                TextField(
                    value = gender,
                    onValueChange = { gender = it },
                    label = { Text("Gender") }
                )
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    val newUser = User(name = name, age = age.toIntOrNull() ?: 0, gender = gender)
                    onAddUser(newUser)
                    onDismiss()
                }
            ) {
                Text("Add")
            }
        },
        dismissButton = {
            Button(
                onClick = onDismiss
            ) {
                Text("Cancel")
            }
        }
    )
}


