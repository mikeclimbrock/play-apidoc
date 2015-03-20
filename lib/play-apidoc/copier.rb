module PlayApidoc

  class Copier

    TEXT_EXTENSIONS = ['apidoc', 'html', 'scala']

    def initialize(variables)
      @variables = variables
    end

    def mkdir(target)
      `mkdir -p #{target}`
    end

    def copy_file(source, target)
      if text_file?(source)
        File.open(target, "w") do |out|
          IO.readlines(source).each do |l|
            out << substitute(l)
          end
        end
      else
        `cp #{source} #{target}`
      end
    end

    def copy_dir(source, target)
      Dir.entries(source).each do |f|
        next if f == "." || f == ".."
        if File.directory?(File.join(source, f))
          subdir = File.join(target, f)
          mkdir(subdir)
          copy_dir(File.join(source, f), subdir)
        else
          copy_file(File.join(source, f), File.join(target, f))
        end
      end
    end

    private
    def substitute(value)
      tmp = value.dup
      @variables.each do |k, v|
        tmp = tmp.gsub(/\%\%#{k.to_s.upcase}\%\%/, v)
      end
      tmp
    end

    def text_file?(source)
      ext = if md = source.match(/.*\.(.+)$/)
              md[1]
            else
              nil
            end
      TEXT_EXTENSIONS.include?(ext)
    end

  end

end
