# Azure Infrastructure Deployment with Terraform

Este projeto usa Terraform para provisionar recursos no Azure, incluindo:

- Grupo de Recursos
- Virtual Network e Subnet
- IP Público
- Network Security Group (NSG) com regras SSH e HTTP
- Storage Account para diagnostics
- Chaves SSH
- VM Ubuntu 22.04

---

## Pré-requisitos

- Conta Azure e Azure CLI configurada (`az login`)
- Terraform instalado (versão >= 1.0)
- Chave SSH pública existente (`~/.ssh/id_rsa.pub`)

---

## Como usar

1. Clone ou copie os arquivos deste projeto para sua máquina local.

2. Personalize variáveis no arquivo `variables.tf` se desejar mudar configurações padrão.

3. Inicialize o Terraform:

```bash
terraform init
terrafrom plan
terraform apply
ssh -i ~/.ssh/id_rsa azureuser@<IP_PUBLICO>
```

4. Conecte-se à VM via SSH:
```bash
ssh -i ~/.ssh/id_rsa azureuser@<IP_PUBLICO>
``

5. Para destruir os recursos
```bash
terraform destroy
``