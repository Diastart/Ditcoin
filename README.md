<div align="center">

# 💰 DITCOIN

### *A Java-Based Blockchain Simulation Engine*

```
┌─────────────────────────────────────────────────────────┐
│  Block #1                Block #2                Block #3│
│  ┌────────┐              ┌────────┐              ┌────────┐
│  │ Merkle │──hash────────▶│ Merkle │──hash────────▶│ Merkle │
│  │  Root  │              │  Root  │              │  Root  │
│  └────────┘              └────────┘              └────────┘
│     PoW                     PoW                     PoW     │
└─────────────────────────────────────────────────────────┘
```

[![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)](https://www.java.com/)
[![License](https://img.shields.io/badge/License-MIT-blue.svg?style=for-the-badge)](LICENSE)
[![Blockchain](https://img.shields.io/badge/Blockchain-121D33?style=for-the-badge&logo=blockchain.com&logoColor=white)](https://bitcoin.org/bitcoin.pdf)
[![Status](https://img.shields.io/badge/Status-Active-success?style=for-the-badge)](https://github.com)

</div>

---

## 🚀 What is Ditcoin?

**Ditcoin** is a comprehensive blockchain simulation that emulates Bitcoin's core functionality. Built entirely in Java, it demonstrates the fundamental concepts of cryptocurrency including proof-of-work mining, transaction validation, Merkle tree construction, and wallet management—all in a single, elegant codebase.

This isn't just another "hello blockchain" tutorial. Ditcoin simulates a **real-time cryptocurrency network** with autonomous users who trade and mine concurrently, showcasing the distributed nature of blockchain technology.

---

## ✨ Features

### 🔐 **Cryptographic Security**
- **RSA Key Pairs** (512-bit) for wallet authentication
- **SHA-256 Hashing** for block and transaction integrity
- **Digital Signatures** via public/private key validation

### ⛏️ **Proof-of-Work Mining**
- Difficulty-based mining with nonce calculation
- Competition between miners for block rewards
- Dynamic reward system (0.25 coins per block)

### 🌳 **Merkle Tree Implementation**
- Efficient transaction aggregation
- Cryptographic transaction validation
- Binary tree hashing structure

### 🔗 **Blockchain Architecture**
- Immutable block chain with hash linking
- Genesis block initialization
- Previous hash validation

### 💳 **Wallet System**
- Dynamic address generation
- Balance tracking
- Secure key rotation

### 👥 **Multi-Agent Simulation**
- **TRADERS**: Autonomous users who perform random transactions
- **MINERS**: Compete to solve blocks and earn rewards
- 10 concurrent users by default
- Real-time transaction pooling

### ⏱️ **Timed Block Generation**
- 10-second block intervals
- Transaction queue management
- Synchronized mining rounds

---

## 🏗️ Architecture

```
┌──────────────────────────────────────────────────────────────┐
│                         DITCOIN                              │
├──────────────────────────────────────────────────────────────┤
│                                                              │
│  ┌────────────┐    ┌──────────────┐    ┌────────────────┐  │
│  │   Wallet   │◀───│     User     │───▶│  Transaction   │  │
│  │            │    │  (Trader/    │    │                │  │
│  │ - balance  │    │   Miner)     │    │ - sender       │  │
│  │ - address  │    │              │    │ - receiver     │  │
│  │ - key      │    │ - public_key │    │ - amount       │  │
│  └────────────┘    │ - private_key│    └────────┬───────┘  │
│                    └──────┬───────┘             │          │
│                           │                     │          │
│                           ▼                     ▼          │
│                    ┌─────────────┐      ┌──────────────┐  │
│                    │  Community  │◀────▶│ MerkleTree   │  │
│                    │             │      │              │  │
│                    │ - map       │      │ - rootTx     │  │
│                    │ - txPool    │      │ - hash()     │  │
│                    └──────┬──────┘      └──────────────┘  │
│                           │                                │
│                           ▼                                │
│                    ┌─────────────┐      ┌──────────────┐  │
│                    │ BlockChain  │◀────▶│    Block     │  │
│                    │             │      │              │  │
│                    │ - blocks[]  │      │ - id         │  │
│                    │ - addBlock()│      │ - nonce      │  │
│                    └─────────────┘      │ - hash       │  │
│                                         │ - prevHash   │  │
│                                         │ - merkleRoot │  │
│                                         └──────────────┘  │
│                                                              │
└──────────────────────────────────────────────────────────────┘
```

---

## 🎯 Core Components

| Component | Description | Key Responsibility |
|-----------|-------------|-------------------|
| **`BlockChain.java`** | The ledger | Maintains the immutable chain of blocks |
| **`Block.java`** | Individual block | Stores transactions, hash, and proof-of-work |
| **`Transaction.java`** | Value transfer | Handles coin movement between users |
| **`Wallet.java`** | User account | Stores balance and cryptographic keys |
| **`MerkleTree.java`** | Transaction hasher | Creates efficient transaction summaries |
| **`Community.java`** | Network simulator | Manages users, mining, and transaction pools |
| **`Timer.java`** | Block timer | Synchronizes mining rounds every 10 seconds |
| **`Main.java`** | Entry point | Initializes the network with 10 users |

---

## 🛠️ Installation & Usage

### Prerequisites

- **Java Development Kit (JDK) 11+**
- Basic understanding of blockchain concepts (optional but helpful!)

### Quick Start

```bash
# Clone the repository
git clone https://github.com/yourusername/ditcoin.git
cd ditcoin

# Compile the project
javac src/*.java

# Run the simulation
java -cp src Main
```

### Alternative: Use the clean script

```bash
# Navigate to source directory
cd src

# Make the script executable
chmod +x clean.sh

# Clean and recompile
./clean.sh
```

---

## 📊 Sample Output

When you run Ditcoin, you'll see real-time activity like this:

```
0 👛 ← 73
1 👛 ← 42
2 👛 ← 88
3 ⟶ 💰 7: 12.45
4 ⟶ 💰 2: 8.92
5 got reward 🏆
6 ⟶ 💰 1: 34.67
7 👛 ← 56
8 ⟶ 💰 3: 19.23
3 got reward 🏆
...
```

**Legend:**
- `👛 ← amount` - User topped up their wallet
- `⟶ 💰 recipient: amount` - Transaction sent
- `got reward 🏆` - Miner solved a block

---

## 🎓 How It Works

### 1️⃣ **Network Initialization**
When you run `Main.java`, a community of 10 users is created. Each user is randomly assigned as either a **TRADER** or **MINER**.

### 2️⃣ **Trading Phase**
Traders perform random transactions:
- If balance < 10, they top up (add random coins)
- Otherwise, they send coins to random users
- All transactions go into a waiting pool

### 3️⃣ **Mining Phase**
Every 10 seconds:
- Waiting transactions move to processing queue
- Miners compete to solve the block (find hash starting with "00")
- Winner gets 0.25 Ditcoin reward
- Block is added to the blockchain

### 4️⃣ **Security**
- Each transaction is signed with sender's private key
- Wallets regenerate keys after each transaction (key rotation)
- Merkle trees ensure transaction integrity
- Blocks link via SHA-256 hashes

---

## 🧪 Key Algorithms

### Proof-of-Work Mining

```java
do {
    String blockHash = id + data + nonce + previousHash;
    currentHash = SHA256(blockHash);
    nonce++;
} while (!currentHash.startsWith("00"));
```

The difficulty is set to 2 leading zeros. Miners iterate through nonces until they find a valid hash.

### Merkle Tree Construction

```java
while (transactions.size() > 1) {
    for (int i = 0; i < transactions.size(); i += 2) {
        hash = SHA256(transactions[i] + transactions[i+1]);
        newLevel.add(hash);
    }
    transactions = newLevel;
}
rootHash = transactions[0];
```

Transactions are paired and hashed recursively until a single root hash remains.

---

## 🔬 Exception Handling

Ditcoin includes custom exceptions for robust error management:

| Exception | Trigger | Purpose |
|-----------|---------|---------|
| `UserNotFoundException` | Invalid recipient address | Prevents sending to non-existent users |
| `NotEnoughBalanceException` | Insufficient funds | Prevents overdrafts |
| `SelfTransactionException` | User sends to themselves | Prevents pointless transactions |

---

## 📂 Project Structure

```
ditcoin/
│
├── src/
│   ├── Main.java                    # Entry point
│   ├── BlockChain.java              # Blockchain manager
│   ├── Block.java                   # Block data structure
│   ├── Transaction.java             # Transaction logic
│   ├── Wallet.java                  # Wallet implementation
│   ├── MerkleTree.java              # Merkle tree builder
│   ├── Community.java               # Network simulator
│   ├── Timer.java                   # Block timer
│   ├── UserNotFoundException.java
│   ├── NotEnoughBalanceException.java
│   ├── SelfTransactionException.java
│   └── clean.sh                     # Build script
│
├── subject/
│   ├── bitcoin.pdf                  # Bitcoin whitepaper
│   └── bitcoin_specification.pdf    # Project specifications
│
└── README.md                        # This file
```

---

## 🧩 Customization

Want to modify the simulation? Here are some ideas:

### Change Number of Users
In `Main.java:8`:
```java
for(int i = 0; i < 20; i++) {  // Changed from 10 to 20
    users.add(comunity.new User());
}
```

### Adjust Mining Difficulty
In `Block.java:13`:
```java
private final int difficulty = 4;  // Requires "0000" prefix
```

### Modify Block Time
In `Community.java:15`:
```java
Timer timer = new Timer("timer", 30);  // 30 seconds instead of 10
```

### Change Mining Reward
In `Community.java:28`:
```java
return 0.50;  // 0.50 coins instead of 0.25
```

---

## 🎯 Educational Goals

This project demonstrates:

- ✅ **Blockchain fundamentals** - Immutable ledger with linked blocks
- ✅ **Cryptographic hashing** - SHA-256 for security
- ✅ **Proof-of-Work consensus** - Decentralized mining competition
- ✅ **Public-key cryptography** - RSA key pairs for authentication
- ✅ **Merkle tree data structures** - Efficient transaction verification
- ✅ **Concurrent programming** - Multi-threaded user simulation
- ✅ **Transaction validation** - Balance checks and signature verification

---

## 📚 Learning Resources

Want to dive deeper? Check these out:

- 📄 [Bitcoin Whitepaper](https://bitcoin.org/bitcoin.pdf) - The original Satoshi Nakamoto paper
- 📖 [Mastering Bitcoin](https://github.com/bitcoinbook/bitcoinbook) - Comprehensive guide
- 🎥 [Blockchain Demo](https://andersbrownworth.com/blockchain/) - Interactive visualization
- 🔐 [Cryptographic Hash Functions](https://en.wikipedia.org/wiki/Cryptographic_hash_function)
- 🌳 [Merkle Trees Explained](https://en.wikipedia.org/wiki/Merkle_tree)

---

## 🐛 Known Limitations

This is a **simulation** designed for education, not production use:

- ⚠️ **Simplified security**: Real blockchains use more sophisticated cryptography
- ⚠️ **No network layer**: Runs on a single machine, not distributed
- ⚠️ **No persistence**: Blockchain resets on restart (no database)
- ⚠️ **Fixed difficulty**: Real chains adjust difficulty dynamically
- ⚠️ **No transaction fees**: Miners only get block rewards
- ⚠️ **Simple consensus**: Real systems handle forks and conflicts

---

## 🤝 Contributing

Contributions are welcome! Here are some ideas:

- 🎨 Add a GUI to visualize the blockchain
- 💾 Implement blockchain persistence (save to file/database)
- 🌐 Create a network layer for multi-machine simulation
- 📊 Add transaction fee system
- 🔄 Implement dynamic difficulty adjustment
- 🧪 Write comprehensive unit tests
- 📱 Create a REST API for blockchain queries

### How to Contribute

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

---

## 📜 License

This project is open source and available under the [MIT License](LICENSE).

---

## 👨‍💻 Author

Created with ☕ and curiosity as an educational exploration of blockchain technology.

---

## 🙏 Acknowledgments

- **Satoshi Nakamoto** - For inventing Bitcoin and inspiring this project
- **Bitcoin Community** - For extensive documentation and resources
- **Java Community** - For robust libraries and tools

---

<div align="center">

### 💎 *"In cryptography we trust"*

**If you found this project helpful, give it a ⭐!**

[![GitHub stars](https://img.shields.io/github/stars/yourusername/ditcoin.svg?style=social&label=Star)](https://github.com/yourusername/ditcoin)
[![GitHub forks](https://img.shields.io/github/forks/yourusername/ditcoin.svg?style=social&label=Fork)](https://github.com/yourusername/ditcoin/fork)

---

**Built with ❤️ by blockchain enthusiasts, for blockchain enthusiasts**

</div>
