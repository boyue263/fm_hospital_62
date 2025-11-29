# 创建/覆盖 README.md 文件
@"
# FM Hospital 医疗在线平台

![Java](https://img.shields.io/badge/Java-15-orange)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-2.5.2-brightgreen)
![License](https://img.shields.io/badge/License-MIT-blue)

## 项目简介

FM Hospital 是一个基于 Spring Boot 的医疗在线平台，提供医院管理、患者服务、在线咨询等功能。本项目主要用于演示开源协作流程和 Git 分支管理实践。

## 🚀 核心功能

- **患者管理** - 患者信息维护、病历管理
- **医生排班** - 医生工作安排、预约管理
- **药品库存** - 药品信息、库存管理
- **在线咨询** - 医患在线交流平台

## 🛠 技术栈

- **后端**: Spring Boot 2.5.2 + MyBatis + MySQL
- **前端**: Thymeleaf + Bootstrap
- **安全**: Sa-Token 权限认证
- **缓存**: Redis
- **构建工具**: Maven 多模块

## 📁 项目结构

\`\`\`
fm_hospital/
├── common/                 # 通用模块
├── hospital_hms_api/       # 医院管理系统API
├── hospital_wx_api/        # 微信小程序API
├── .github/
│   └── ISSUE_TEMPLATE/     # Issue模板
└── README.md
\`\`\`

## 🚦 快速开始

### 环境要求
- JDK 15+
- MySQL 8.0+
- Maven 3.6+

### 运行步骤
\`\`\`bash
# 克隆项目
git clone https://github.com/boyue263/fm_hospital_62.git

# 导入IDE
mvn clean install

# 配置数据库
# 复制 application-example.yml 为 application.yml 并修改配置

# 启动项目
mvn spring-boot:run
\`\`\`

## 🤝 参与贡献

我们欢迎所有形式的贡献！请阅读 [贡献指南](CONTRIBUTING.md) 开始参与。

1. Fork 本项目
2. 创建 Feature 分支 (\`git checkout -b feature/AmazingFeature\`)
3. 提交更改 (\`git commit -m 'Add some AmazingFeature'\`)
4. 推送到分支 (\`git push origin feature/AmazingFeature\`)
5. 开启 Pull Request

## 📄 开源协议

本项目采用 MIT 协议 - 查看 [LICENSE](LICENSE) 文件了解详情。

## 📞 联系我们

- 项目主页: https://github.com/boyue263/fm_hospital_62
- Issues: https://github.com/boyue263/fm_hospital_62/issues

---
