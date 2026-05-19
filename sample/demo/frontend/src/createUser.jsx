import { useState } from "react";

function CreateUser() {

    const [username, setUsername] = useState("");
    const [email, setEmail] = useState("");

    const handleCreateUser = async () => {

        const response = await fetch("http://localhost:8080/api/users", {
            method: "POST",

            headers: {
                "Content-Type": "application/json"
            },

            body: JSON.stringify({
                username: username,
                email: email
            })
        });

        const data = await response.json();

        console.log(data);

        alert("User Created");
    };

    return (
        <div>

            <h1>Create User</h1>

            <input
                type="text"
                placeholder="Enter Username"
                value={username}
                onChange={(e) => setUsername(e.target.value)}
            />

            <br />
            <br />

            <input
                type="email"
                placeholder="Enter Email"
                value={email}
                onChange={(e) => setEmail(e.target.value)}
            />

            <br />
            <br />

            <button onClick={handleCreateUser}>
                Create User
            </button>

        </div>
    );
}

export default CreateUser;