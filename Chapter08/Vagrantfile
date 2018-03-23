# -*- mode: ruby -*-
# vi: set ft=ruby :

VAGRANTFILE_API_VERSION = "2"

Vagrant.configure(VAGRANTFILE_API_VERSION) do |config|
    config.vm.box = "ubuntu/trusty64"
    config.vm.synced_folder ".", "/vagrant"
    config.vm.provider "virtualbox" do |v|
        v.memory = 1024
    end
    config.vm.network :forwarded_port, host: 9001, guest: 9001
    config.vm.network :forwarded_port, host: 9002, guest: 9002
    config.vm.provision "docker" do |d|
        d.run "books-fe",
            image: "vfarcic/books-fe",
            args: "-e MODE=mock_server -e MOCK_PORT=9002 -p 9001:8080 -p 9002:9002"
    end
end